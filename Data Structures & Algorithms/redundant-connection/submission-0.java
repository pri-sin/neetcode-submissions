class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int[] parent=new int[edges.length+1];

        int n=edges.length;

        for(int i=0;i<n;i++){
            parent[i]=i;
        }

        for(int []edge:edges){
            int root1=find(parent, edge[0]);
            int root2=find(parent, edge[1]);

            if(root1==root2){
                return edge;
            }

            parent[root1]=root2;
        }

        return new int[0];
    }

    public int find(int []parent, int i){
        if(parent[i]==i){
            return i;
        }
        return parent[i]=find(parent, parent[i]);
    }

}
