class Solution {
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count=0;
        int mins=0;

        Queue<int []> q=new ArrayDeque<>();

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==2){
                    q.offer(new int[]{i,j});
                }
                if(grid[i][j]==1){
                    count++;
                }
            }
        }
        int [][]directions={{1,0},{-1,0},{0,1},{0,-1}};

        while(!q.isEmpty() && count!=0){
            mins=mins+1;
            int size=q.size();
            for(int i=0;i<size;i++){
                int []curr=q.poll();
                int r=curr[0];
                int c=curr[1];
                for(int []dir:directions){
                    int dr=r+dir[0];
                    int dc=c+dir[1];
                    if(dr>=0 && dr<m && dc>=0 && dc<n && grid[dr][dc]==1){
                        count=count-1;
                        grid[dr][dc]=2;
                        q.offer(new int[]{dr,dc});
                    }
                }
            } 
        }
        return count==0?mins:-1;
    }
}
