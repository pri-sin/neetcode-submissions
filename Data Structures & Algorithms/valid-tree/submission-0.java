
class Solution {
    public boolean validTree(int n, int[][] edges) {
        // A tree with n nodes must have exactly n - 1 edges
        if (edges.length != n - 1) {
            return false;
        }

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];

        // Perform DFS starting from node 0 with no parent (-1)
        if (!dfs(graph, 0, -1, visited)) {
            return false; // Cycle detected
        }

        // Check if all nodes are connected
        for (boolean isVisited : visited) {
            if (!isVisited) {
                return false;
            }
        }

        return true;
    }

    private boolean dfs(List<List<Integer>> graph, int node, int parent, boolean[] visited) {
        visited[node] = true;

        for (int neighbor : graph.get(node)) {
            // Skip the node we just came from
            if (neighbor == parent) {
                continue;
            }
            // If the neighbor is already visited and it's not the parent, a cycle exists
            if (visited[neighbor] || !dfs(graph, neighbor, node, visited)) {
                return false;
            }
        }

        return true;
    }
}