# Clari `clarius_core` — Developer Onboarding Guide

> A first-week, plain-language tour of the Clari core backend: how to run it
> locally, how it is put together, how code ships to production, and how to
> debug and observe it.
>
> Audience: new backend engineers. No prior Clari knowledge assumed.
> Format: Markdown, optimized for PDF export (plain headers, bullets, ASCII
> diagrams).

---

## 0. The 60-Second Mental Model

- **What it is:** `clarius_core` is a large **Bazel monorepo** of **Java 17**
  services. It holds ~150 modules — shared libraries (e.g. `clari-common`,
  `core-common`), plus deployable services (e.g. `core-service`,
  `auth-service`, `analytics-service`, `query-manager-service`).
- **Runtime shape:** A historical **layered "monolith"** (`core-service`) that
  can boot in many roles, **surrounded by newer microservices** that were
  carved out of it. It is a monorepo-of-services, not a single monolith.
- **Where your code runs:** Each service is a **WAR** deployed on **Jetty**,
  using **Jersey (JAX-RS)** for REST and **Google Guice** for dependency
  injection.
- **Where data lives:** **PostgreSQL** (primary relational store, RDS-style),
  **MongoDB** (documents/config), **Cassandra** (wide-column), **Redis**
  (cache), **Kafka** (events), and **Conductor** (workflow orchestration).
- **Local dev rule of thumb:** Databases run in **Docker containers**; the Java
  service you are working on runs **natively on your host** (via Bazel) and
  talks to the containers over mapped `localhost` ports.

```
        YOUR LAPTOP
 ┌───────────────────────────────────────────────────────────┐
 │  Java service (Bazel + Jetty, native)                       │
 │        │  localhost:5432 / 27017 / 9042 / 6379              │
 │        ▼                                                    │
 │  ┌─────────────── docker compose (clarius_core) ─────────┐  │
 │  │  postgres  mongo(x2)  cassandra  redis  (+bootstraps) │  │
 │  └───────────────────────────────────────────────────────┘ │
 └───────────────────────────────────────────────────────────┘
```

---

## 1. Local Environment Setup & Containers

### 1.1 Prerequisites (one-time)

From `README.md`:

- **JDK:** Amazon **Corretto 17** as the default JDK (Java 17 features are used).
- **AWS access:** SSO configured (`okta-aws-cli web`); needed to pull secrets
  and images. Set `export AWS_REGION=us-east-1`.
- **Container runtime:** A macOS container runtime (see the DEVEXP "Containers
  on macOS" wiki page).
- **Bazel:** Installed with an Artifactory token (same token used for Docker).
- **Env vars:** `export environment=DEVELOPMENT` and an appropriate `TZ`.

### 1.2 Bootstrapping from scratch

The single entry point is **`./init-dev-environment.sh`**. Step by step it:

1. **Fetches secrets** — downloads the encrypted `secrets.env` from
   `s3://clari-dev-setup/core-local-secrets` if not already present locally.
2. **Generates Bazel test config** — runs `scripts/generateBazelTestConfig.sh`.
3. **Tears down old state** — `docker compose down --volumes` (drops all DB
   volumes so you start clean).
4. **Resets data dirs** — recreates `../data` and clears the web temp dir.
5. **Starts containers** — `docker compose up --remove-orphans -d --wait`,
   which brings up every DB service **and** runs the one-shot "bootstrap"
   containers that create databases, roles, and seed data.

Then start a service (see §1.5).

### 1.3 What runs in containers (`docker-compose.yml`)

The compose project is named **`clarius_core`**. All images come from Clari's
JFrog registry. Services fall into two groups: **long-running datastores** and
**one-shot bootstrap/init jobs**.

| Service | Image | Host port | Purpose |
|---|---|---|---|
| `redis` | `redis:3.2` | 6379 | Cache / ephemeral state |
| `pg` | `postgres-for-core-tests` | 5432 | Primary Postgres cluster |
| `pg2` | `postgres-for-core-tests` | 5433 | Second Postgres cluster (V2/STEELIX) |
| `cass` | `cassandra:3` | 9042 | Cassandra wide-column store |
| `mongo1` | `mongo:8` | 27017 | Mongo replica-set member 1 |
| `mongo2` | `mongo:8` | 27018 | Mongo replica-set member 2 |

**One-shot init containers** (they run, do their job, then exit):

- `pg-bootstrap` / `pg2-bootstrap` — create roles + databases and seed them.
- `cass-bootstrap` — applies Cassandra schema.
- `mongo-cluster-keygen` — generates the replica-set keyfile.
- `mongo-bootstrap` — initializes Mongo users/DBs for `DEVELOPMENT` + `TEST`.
- `all-bootstraps` — a no-op aggregator that `depends_on` every bootstrap, so
  `--wait` blocks until all initialization has completed successfully.
- `conductor-test` — **never started** (profile `image-cache-only`); it exists
  only so CI pre-caches the Conductor image for Testcontainers.

> **Note on architecture:** In local dev, only the **DBs** are containerized.
> The Clari application(s) run on the host and reach the DBs by port mapping.

### 1.4 Postgres: containers, volumes, init scripts, seed data

Postgres is the most involved. It is deliberately configured to **emulate AWS
RDS** so local behaves like production.

- **Startup flags:** `postgres -c fsync=off -c synchronous_commit=off` — durability
  is traded for speed (fine for disposable dev data).
- **Persistent volumes:** `pg` and `pg2` hold `/var/lib/postgresql`; `pgtmp` is a
  shared scratch volume. Because state is on named volumes, `docker compose down`
  (without `--volumes`) preserves your data across restarts; `--volumes` wipes it.
- **Init scripts** (in `docker-compose-scripts/`, run by the bootstrap containers):
  - `pg-init-aws-roles.sh` — creates RDS-style roles (`rds_superuser`,
    `rds_password`, `rds_replication`) and the `datamart` "super" login user,
    mirroring the most-privileged account available in AWS.
  - `pg-init-cluster.sh` — creates the per-environment **default DB** and Clari
    global groups (`clari_global_admins/writers/readers`), via `init_cluster.sql`.
  - `pg-init-bootstrap-cluster.sh` — additionally creates the
    `clarius_bootstrap_<env>` DB and runs `bootstrap.sql` to **seed** the
    service/resource registry (the tables that tell the app where every other
    DB lives). Invoked as `DEVELOPMENT _dev` and `TEST _test`.
- **Seed data:** `bootstrap.sql` (+ `sid_mapper*.sql`, `1_service_resource.sql`)
  registers Postgres and Mongo clusters in `resource_dim` so the app can resolve
  connections. DB names carry a JSON `COMMENT` describing environment/org/service.

### 1.5 Essential local CLI commands

```shell
# Full clean bootstrap (secrets + fresh containers + seed)
./init-dev-environment.sh

# Start the containers only (idempotent; --wait blocks on health + bootstraps)
docker compose up --remove-orphans -d --wait

# Stop containers but KEEP database volumes/state
docker compose down

# Stop AND wipe all database volumes (full reset)
docker compose down --volumes

# Run a service natively via Bazel:  <service> <component> <httpPort>
./start-local-bazel.sh core-service core 8080

# Prune leftover docker networks/volumes
docker network prune -f && docker volume prune -f
```

**Connecting to the datastores** (thin wrappers in `docker-bin/` run the client
inside a throwaway container so you need nothing installed locally):

```shell
# Postgres — 'datamart' is the limited superuser (same password)
docker-bin/psql -U datamart -h localhost -p 5432 --password clarius_bootstrap_test
#   inside psql:  \l+   lists every DB (read the COMMENT for a human-readable label)

# Mongo (localhost:27017) — or point Studio 3T at it
docker-bin/mongosh

# Cassandra
docker-bin/cqlsh

# Redis
redis-cli -h localhost -p 6379 ping
```

To find the server key used by the manifest/health endpoint:

```shell
cat secrets.env | grep CLARIUS_CORE_SERVERKEY
# then visit: http://localhost:8080/system/manifest?cardserver_key=XXXX
```

---

## 2. High-Level System Architecture

### 2.1 Architectural patterns

- **Monorepo of services (Bazel).** One repo, many independently deployable
  units. Shared code lives in library modules; each service module produces a
  WAR + container image.
- **Layered architecture inside each service:**
  `Filters → Resource (JAX-RS) → Service → Persistence`.
- **A multi-role "core" monolith + extracted microservices.** `core-service`
  is a single codebase that boots into one of many **components** (see
  `ClariusComponent`): `CORE` (the API server), `INDEXER`, `CRAWLER`,
  `QUEUE_WORKER`, `EXTRACT_WORKER`, `LOAD_WORKER`, `AC_WORKER`, etc. The
  component is chosen at launch via `-Dcomponent.mode=...` / the
  `CLARIUS_COMPONENT` env var. Newer domains (auth, config, analytics, ingest,
  matching, query-manager, …) are their own service modules.
- **Event-driven + workflow orchestration.** Kafka carries domain events;
  **Netflix Conductor** drives DAG-based background jobs consumed by the worker
  components.
- **Dependency Injection everywhere.** Guice wires the object graph; JAX-RS
  resources and servlet filters are Guice-managed singletons.

### 2.2 Key layers & the request lifecycle (the `CORE` component)

Startup: Jetty boots the WAR → `web.xml` registers the **`GuiceFilter`** and the
**`ClariusServletContextListener`**. The listener builds the Guice injector,
initializes datastore access, and (for worker components) starts schedulers,
Kafka consumers, and Conductor workers. `ClariServletModule` mounts the Jersey
servlet and the filter chain, and registers all REST resource packages
(`com.clarius.cards.resource`, `com.clarius.api`, `com.clarius.crmds.resources`,
…).

Every inbound request passes through this chain:

```
HTTP request
   │
   ▼
[ Jetty ] ──► [ GuiceFilter ] ──► filter chain:
                                   AuthFilter
                                   TelemetryFilter        (tracing/metrics)
                                   ClientVersionValidationFilter
                                   CurrentUserFilter      (who is calling)
                                   MultiOrgUserFilter     (tenant/org scoping)
                                   IPRestrictionFilter
   │                               DemoServletFilter
   ▼
[ Jersey Resource ]   e.g. GET /resources/api/... (a @Path class)
   │
   ▼
[ Service layer ]     business logic (Guice-injected collaborators)
   │
   ▼
[ Persistence ]  ──►  Postgres (RDSAccessManager) / Mongo / Cassandra / Redis
   │
   ▼
JSON response  ◄── (errors normalized by com.clarius.common.errors framework)
```

Text walk-through of an API call reaching the database:

1. Request hits Jetty on the service port (e.g. `8080`).
2. `GuiceFilter` hands off to the ordered servlet filters. `AuthFilter`
   authenticates; `CurrentUserFilter`/`MultiOrgUserFilter` establish the user
   and org (multi-tenant); `TelemetryFilter` opens a trace span and MDC context.
3. Jersey routes the URL to the matching `@Path` **resource** method.
4. The resource delegates to a **service** class (injected by Guice).
5. The service reads/writes through the persistence layer. Relational access
   goes through `RDSAccessManager`; the **bootstrap `resource_dim` registry**
   (seeded in §1.4) tells the app which physical Postgres/Mongo cluster and DB
   to connect to for that org/service.
6. The response is serialized to JSON; failures flow through the shared error
   framework.

### 2.3 Data stores at a glance

| Store | Role | Local port |
|---|---|---|
| PostgreSQL | Primary relational data; RDS-emulated, multi-DB per org/service | 5432 / 5433 |
| MongoDB | Documents, user state, config, saved views (replica set) | 27017 / 27018 |
| Cassandra | High-volume wide-column data | 9042 |
| Redis | Caching / transient coordination | 6379 |
| Kafka | Event streaming between services | (per-service compose) |
| Conductor | Workflow/DAG orchestration for workers | (Testcontainers/CI) |

### 2.4 Database migrations

The **`migrations`** module holds programmatic migrations, split by store:

- `migrations/.../mongo/` — Mongo index/collection/schema migrations (the bulk),
  driven by `Migration`/`Migrations` handlers.
- `migrations/.../psql/` — Postgres data migrations.
- `migrations/.../datastore/` — cross-store schema migrations.
- `migrations/src/main/resources/migrations/mongo/*.json` — declarative schema
  docs (e.g. Okta user schemas).
- Base SQL schema/bootstrap lives under
  `clari-common/src/main/resources/schema/` and is applied at bootstrap time.

Migrations are exposed/triggered through `MigrationResource` and run as part of
service startup/operational tooling rather than a standalone Flyway CLI. PRs
that include schema changes must flag "This change includes database
migrations" in the PR checklist.

---

## 3. Deployment & CI/CD

CI/CD is **GitHub Actions** (`.github/workflows/`). Runners are Clari's
self-hosted **Kubernetes (EKS)** runner groups (e.g. `k8s-group-l`,
`k8s-group-arm64-s`).

### 3.1 From pull request → merge (CI)

Main workflow: **`clarius-core-ci.yaml`** ("Bazel tests"), triggered on PRs to
`master`/`main`/`prod-release-*` and on merge-queue groups.

- **Affected-target detection:** Uses **`bazel-diff`** to hash the target graph
  of the base branch vs. the PR branch and compute exactly which targets (and
  therefore which tests) are impacted. Only impacted tests run — not the whole
  repo.
- **Sharding:** Impacted tests are split into shards (≤24 tests each, up to 255
  shards) and run in parallel via the reusable `clarius-core-ci-test-shard.yaml`
  (matrix, `max-parallel: 96`). Special buckets: **L0** (no-Docker) and **L1 /
  isolated** tests get dedicated shards.
- **Docker image cache:** `cache-docker-images` pre-pulls every image in
  `docker-compose.yml` and stores them in S3 so Testcontainers start fast.
- **Compile gate:** `test-compile` runs `bazelisk build --config=ci_gha_eks //...`.
- **Coverage:** shard LCOV files are merged; a PR comment reports diff coverage
  (min threshold `75.0`).
- **Gate:** `all-bazel-tests-pass-on-eks` is the required check that fails the PR
  if any shard, isolated, L0, setup, or compile job failed.

```
PR opened ──► bazel-diff (affected targets) ──► shard impacted tests
                                                   │
        ┌──────────────┬───────────────┬──────────┴─────────┐
        ▼              ▼               ▼                     ▼
   regular shards  L0 shard      isolated(L1) shard     test-compile //...
        └──────────────┴───────────────┴──────────┬─────────┘
                                                   ▼
                                     all-bazel-tests-pass  (required) ──► merge
```

Other PR-time checks include: `local-pr-title-body-check` (enforces the Jira-ID
title format), Buf/protobuf lint, TruffleHog secret scanning, code-ownership,
Buildifier lint, and cyclic-dependency checks.

### 3.2 From merge → staging → production (CD)

Main workflow: **`clarius-core-cd.yaml`** ("clarius-core-cd-pipeline"),
triggered on **push to `master`**:

1. **`set-job-parameters`** — `clari/cicd-actions/tag-and-release` creates the
   version tag / GitHub Release and collects the PR numbers in the release.
2. **`build-and-publish-protobuf`** — publishes protobuf artifacts.
3. **`build-and-publish-artifacts`** — Bazel builds the service WARs + OCI
   images and pushes them to **ECR** (`374926383693.dkr.ecr.us-east-1.amazonaws.com/clari/...`).
4. **`deploy`** — calls the shared reusable workflow
   `clari/cicd/.github/workflows/global-deploy-services.yaml@v4.4.27` with
   `env: dev`. This is the org-wide deployer.
5. **`run-smoke-tests`** — smoke tests against `prestaging`.
6. **`affected_services`** — computes which services changed (for
   notifications / targeted follow-ups).

**Promotion to stage/prod** is done via the manual **`deploy.yaml`**
(`workflow_dispatch`, choose `dev` / `stage` / `prod` + version + optional
service list) and **`hotfix.yaml`** (prod-only, tags off a release branch, then
builds + deploys). Both call the same `global-deploy-services` reusable workflow.

### 3.3 Deploy targets & infrastructure

Targets are declared in **`.github/deployments.json`**. Two runtime types are
used:

- **`k8s`** — the majority of services deploy to **Kubernetes** (each with its
  own `namespace`, ECR `image`, and a `deployment` health verification). E.g.
  `auth-service`, `config-service`, `analytics-service`, `query-manager-service`,
  the `ingest-*` services, and most `core-service` worker roles
  (`crawler-service`, `indexer-worker-service`, `queue-worker-service`, …).
- **`ec2`** — a few heavier/legacy roles run on **EC2**, e.g.
  `clarius-core-core` (the main API), `clarius-core-indexer`, and
  `tools-service`. Some roles (e.g. `load-worker`, `queue-worker`) deploy to
  **both** EC2 and k8s and can differ per environment (`dev` / `stage` /
  `default`).
- Each deployment can carry `metadata.slack` for on-call alerting.

### 3.4 Secrets & environment variables

- **Local:** `secrets.env` — encrypted, stored in S3, auto-downloaded by
  `init-dev-environment.sh`, and sourced via `scripts/secrets.sh` at launch.
  Never commit it; if it changes, engineers re-download.
- **CI/CD:** GitHub Actions **secrets** (`ACTIONS_ARTIFACTORY_TOKEN`,
  `GHA_WORKFLOWS_TOKEN`, app tokens) and **vars** (`ACTIONS_ARTIFACTORY_USER`,
  registry). OIDC (`id-token: write`) is used for cloud auth.
- **Runtime config:** environment variables per deployment, plus **LaunchDarkly**
  feature flags for progressive rollout (there are dedicated LD workflows for
  code-refs, alerts, and cleanup Jiras).
- **Secret hygiene:** TruffleHog scans PRs; never put secrets in logs, args, or
  tickets.

---

## 4. Debugging & Observability

### 4.1 Debugging locally (attach a breakpoint)

The launch scripts have first-class remote-debug support (JDWP):

```shell
# Start the service with a debugger listening (defaults: DEBUG_PORT=9999)
DEBUG_MODE=1 ./start-local-bazel.sh core-service core 8080

# Custom debug port (4th arg):
DEBUG_MODE=1 ./start-local-bazel.sh core-service core 8080 5005
```

Under the hood `start-local-bazel.sh` builds the WAR with Bazel, drops the
Jetty context XML into `jetty/base/<service>-webapp/`, and (in debug mode) adds
`-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=<port>`.

Then in **IntelliJ**: *Run → Edit Configurations → + → Remote JVM Debug*, host
`localhost`, port `9999` (or your chosen port), and click Debug to attach. Set
breakpoints as usual. (`suspend=n` means the app starts immediately; switch to
`suspend=y` in the script if you need to break during startup.)

> Several standalone services provide their own `run-local*.sh` /
> `start-local.sh` with debug flags (e.g. `revdb-eventbus-service` exposes JDWP
> on `5005`).

### 4.2 Reading container logs & execing into containers

```shell
# Tail logs for one datastore
docker compose logs -f pg          # or: mongo1, cass, redis

# See status/health of every container in the project
docker compose ps

# Inspect a one-shot bootstrap that failed
docker compose logs pg-bootstrap

# Open a shell inside a running container
docker compose exec pg bash

# Run an ad-hoc query without a shell (client wrappers)
docker-bin/psql -U datamart -h localhost -p 5432 --password clarius_bootstrap_test
docker-bin/mongosh
docker-bin/cqlsh
```

The service you launched with `start-local-bazel.sh` runs **on the host**, so
its logs stream to the terminal where you started it (Jetty stdout).

### 4.3 Logging

- **Framework:** SLF4J over **Logback** (`logback.xml` per service; a few older
  modules use `log4j.properties`).
- **Structured context (MDC):** log lines are tagged with `orgId`, `userId`,
  `impersonatorUserId`, `requestId`, and job/loader/workflow IDs — invaluable
  for tracing a single tenant's request across the logs.
- **Runtime-tunable levels:** the `DynamicMDCFilter` turbo-filter lets you
  change log verbosity at runtime via the `.../system/logger/...` API
  (`LoggerResource`) without a restart.
- **Level control:** `com.clarius` defaults to `DEBUG` locally; the
  `CLARI_LOG_LEVEL` env var overrides it to `INFO` in production. An
  `EnvironmentFilter` restricts the console appender to
  `DEVELOPMENT,DOCKERDEV,TEST`.

### 4.4 Tracing, metrics & monitoring

- **OpenTelemetry:** services can run with the OTEL Java agent (Clari's
  `otel-extension`). The launch scripts include commented `-javaagent` +
  `OTEL_*` options you can enable locally (e.g.
  `-Dotel.traces.exporter=logging`). Images set `OTEL_RESOURCE_ATTRIBUTES` with
  the git commit for version tagging.
- **`TelemetryFilter`** opens a span/metrics context for every HTTP request in
  the filter chain (see §2.2).
- **OTLP Collector:** `query-stats-service` ships an
  `otlp-collector-config.yaml` accepting OTLP/Jaeger/Zipkin/Prometheus and
  batching traces/metrics/logs — the template for how telemetry is exported.
- **Datadog / Splunk:** container images set `DD_SERVICE_PREFIX` (Datadog
  service naming); CI ships PR/build stats to a Splunk HEC endpoint
  (`OTEL_EXPORTER_HEC_ENDPOINT`).
- **Metrics client:** application code emits metrics through
  `com.clarius.common.telemetry.metrics.MetricsClient`.

---

## 5. Quick Reference / First-Day Cheat Sheet

```shell
# 1. One-time-per-clone bootstrap
export environment=DEVELOPMENT AWS_REGION=us-east-1
okta-aws-cli web
./init-dev-environment.sh

# 2. Run the API server
./start-local-bazel.sh core-service core 8080
#    health check: http://localhost:8080/system/manifest?cardserver_key=<key>

# 3. Run tests for a module / single test (Bazel)
bazel test //fc-workflow:all
bazel test //fc-workflow:fc-workflow-src/test/java/com/clarius/fcw/template/service/FcwGetTemplateServiceTest
#    find a target: bazel query //fc-workflow/... | grep -i <TestName>
#    logs: bazel-out/darwin_arm64-fastbuild/testlogs/<target>/test.log

# 4. Debug
DEBUG_MODE=1 ./start-local-bazel.sh core-service core 8080   # attach IntelliJ Remote JVM Debug :9999

# 5. Reset the world
docker compose down --volumes && ./init-dev-environment.sh
```

**Where to look when…**

| You need to… | Look at |
|---|---|
| Understand local containers | `docker-compose.yml`, `docker-compose-scripts/` |
| Bootstrap / reset locally | `init-dev-environment.sh` |
| Find the API entry point | `core-service/.../web.xml`, `ClariServletModule`, `ClariusServletContextListener` |
| See all runtime roles | `ClariusComponent` (in `clari-base`) |
| Understand CI | `.github/workflows/clarius-core-ci.yaml` |
| Understand CD / deploys | `.github/workflows/clarius-core-cd.yaml`, `.github/deployments.json` |
| Change DB schema | `migrations/`, `clari-common/.../resources/schema/` |
| Adjust logging | per-service `logback.xml`, `LoggerResource` runtime API |

> **Golden rules:** run relevant Bazel tests before pushing; scope Bazel to a
> module (never `//...` locally for build/test); keep secrets out of code and
> logs; and put risky changes behind a LaunchDarkly flag.
