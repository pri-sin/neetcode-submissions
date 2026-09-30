class Solution {
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        Map<Integer, List<Integer>> adj=new HashMap<>();
        for(int i=0;i<n;i++){
            adj.put(i, new ArrayList<>());
        }
        for(int []edge:edges){
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        return dfs(adj,hasApple, 0, -1);
    }

    public int dfs(Map<Integer, List<Integer>> adj, List<Boolean> hasApple, int curr, int par){
        int time=0;

        for(int child : adj.get(curr)){
            if(child==par){
                continue;
            }
            int childtime=dfs(adj, hasApple, child, curr);

            if(childtime>0 || hasApple.get(child)){
                time+=childtime+2;
            }
        }

        return time;
    }
}