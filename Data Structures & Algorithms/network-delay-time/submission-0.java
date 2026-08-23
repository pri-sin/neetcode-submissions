class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        if(times.length<n-1) return -1;
        Map<Integer, List<int[]>> list = new HashMap<>();
        for (int i = 0; i <= n; i++) {
            list.put(i, new ArrayList<>());
        }

        for (int []edge : times) {
            list.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        int []dist=new int[n+1];
        Arrays.fill(dist,Integer.MAX_VALUE);

        dist[k]= 0;
        pq.offer(new int[]{0, k});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int w = curr[0];
            int u = curr[1];

            if (w > dist[u]) continue;

            for (int[] p : list.get(u)) {
                int v = p[0];
                int weight = p[1];
                
                // Relaxation step
                if (dist[u] + weight < dist[v]) {
                    dist[v]=dist[u]+weight;
                    pq.offer(new int[]{dist[v], v});
                }
            }
        }

        // Mark remaining unreachable nodes as -1
        int time=0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                return -1;
            }
            if(dist[i]>time){
                time=dist[i];
            }
        }

        return time;

    }
}
