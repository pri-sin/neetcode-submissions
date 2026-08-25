/*class Solution {
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
}// Kruskals Algorithm
*/


class Solution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        Map<Integer, List<int []>> adj=new HashMap<>();
        for(int i=0;i<n-1;i++){
            int xi=points[i][0];
            int yi=points[i][1];
            for(int j=i+1;j<n;j++){
                int xj=points[j][0];
                int yj=points[j][1];

                int dist=Math.abs(xi-xj)+ Math.abs(yi-yj);
                adj.computeIfAbsent(i, k -> new ArrayList<>()).add(new int[]{dist, j});
                adj.computeIfAbsent(j, k -> new ArrayList<>()).add(new int[]{dist, i});
            }
        }

        PriorityQueue<int []> pq=new PriorityQueue<>(Comparator.comparingInt(a->a[0]));
        Set<Integer> visited= new HashSet<>();

        int res=0;

        pq.offer(new int[]{0,0}); //we are adding point at 0th index and its dist 0 as it is the first point
        while(visited.size()<n){
            int []curr=pq.poll();

            int distc=curr[0];
            int yc=curr[1];
            if(visited.contains(yc)){
                continue;
            }
            res+=distc;
            visited.add(yc);

            for(int []neighbor : adj.getOrDefault(yc, Collections.emptyList())){
                int distn=neighbor[0];
                int yn=neighbor[1];
                if(!visited.contains(yn)){
                    pq.offer(neighbor);
                }
            }
        }
        return res;
    }
}
