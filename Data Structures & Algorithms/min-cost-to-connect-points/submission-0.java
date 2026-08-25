class Solution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;

        DSU dsu=new DSU(n);

        List<int []> edges=new ArrayList<>();

        for(int i=0;i<n-1;i++){
            for(int j=i+1;j<n;j++){
                int dist=Math.abs(points[j][0]-points[i][0])+
                         Math.abs(points[j][1]-points[i][1]);
                edges.add(new int[]{dist, i, j});
            }
        }

        Collections.sort(edges, (a,b)->Integer.compare(a[0], b[0]));

        int res=0;

        for(int []edge:edges){
            if(dsu.union(edge[1], edge[2])){
                res+=edge[0];
            }
        }
        return res;
    }
}

class DSU{
    int []parent, size;

    DSU(int n){
        parent=new int[n];
        size=new int[n];
        for(int i=0;i<n;i++){
            parent[i]=i;
        }
        Arrays.fill(size,1);
    }

    private int find(int i){
        if(parent[i]!=i){
            parent[i]=find(parent[i]);
        }
        return parent[i];
    }

    private boolean union(int x, int y){
        int px=find(x);
        int py=find(y);
        if(px==py) return false;

        if(size[px]<size[py]){
            int temp=px;
            px=py;
            py=temp;
        }

        size[px]+=size[py];
        parent[py]=px;
        return true;
    }
}
