class Solution {
    public Map<Integer, Integer> shortestPath(int n, List<List<Integer>> edges, int src) {
        Map<Integer, List<int[]>> list = new HashMap<>();
        for (int i = 0; i < n; i++) {
            list.put(i, new ArrayList<>());
        }

        for (List<Integer> edge : edges) {
            list.get(edge.get(0)).add(new int[]{edge.get(1), edge.get(2)});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        Map<Integer, Integer> dist = new HashMap<>();

        // Initialize with infinity instead of -1
        for (int i = 0; i < n; i++) {
            dist.put(i, Integer.MAX_VALUE);
        }

        dist.put(src, 0);
        pq.offer(new int[]{0, src});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int w = curr[0];
            int u = curr[1];

            if (w > dist.get(u)) continue;

            for (int[] p : list.get(u)) {
                int v = p[0];
                int weight = p[1];
                
                // Relaxation step
                if (dist.get(u) + weight < dist.get(v)) {
                    dist.put(v, dist.get(u) + weight);
                    pq.offer(new int[]{dist.get(v), v});
                }
            }
        }

        // Mark remaining unreachable nodes as -1
        for (int i = 0; i < n; i++) {
            if (dist.get(i) == Integer.MAX_VALUE) {
                dist.put(i, -1);
            }
        }

        return dist;
    }  
}