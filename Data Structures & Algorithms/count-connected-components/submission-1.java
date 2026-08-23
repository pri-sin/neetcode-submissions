class Solution {
    public int countComponents(int n, int[][] edges) {
        int count = 0;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];

        // Loop through ALL nodes 0 to n-1
        for (int i = 0; i < n; i++) {
            // ONLY start a new traversal if the node hasn't been visited yet
            if (!visited[i]) {
                traverse(graph, i, visited);
                count++; // Found a new connected component
            }
        }
        
        return count;
    }

    public void traverse(List<List<Integer>> graph, int source, boolean[] visited) {
        visited[source] = true;

        for (int neighbor : graph.get(source)) {
            if (!visited[neighbor]) {
                traverse(graph, neighbor, visited);
            }
        }
    }
}