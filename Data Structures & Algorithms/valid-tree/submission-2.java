
/*class Solution {
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
}*/

//Union Find
class Solution {
    public boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1) return false;

        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        for (int[] edge : edges) {
            int root1 = find(parent, edge[0]);
            int root2 = find(parent, edge[1]);

            // If both nodes share the same root, adding this edge creates a cycle
            if (root1 == root2) return false;

            parent[root1] = root2; // Union
        }

        return true;
    }

    private int find(int[] parent, int i) {
        if (parent[i] == i) return i;
        return parent[i] = find(parent, parent[i]); // Path compression
    }
}


/*
class Solution {
    public int isTree(int n, int m, int[][] edges) {
        // code here
        if(n-1!=m) return 0;
        
        Map<Integer, List<Integer>> list=new HashMap<>();
        
        for(int i=0;i<n;i++){
            list.put(i, new ArrayList<>());
        }
        
        for(int []edge:edges){
            list.get(edge[0]).add(edge[1]);
            list.get(edge[1]).add(edge[0]);
        }
        
        boolean []visited= new boolean[n];
        return solve(list, visited, 0)==n?1:0;
        
    }
    
    public int solve(Map<Integer, List<Integer>> list,boolean []visited, int root){

        visited[root]=true;
        int count=1;
        
        for(int i:list.get(root)){
            if(!visited[i]){
                count+=solve(list, visited, i);
            }
        }
        return count;
    }
}
*/