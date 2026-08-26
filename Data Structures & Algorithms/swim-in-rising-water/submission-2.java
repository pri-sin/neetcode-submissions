/*class Solution {
    int globalmin=Integer.MAX_VALUE;
    public int swimInWater(int[][] grid) {
        int n = grid.length;

        boolean [][]visited = new boolean[n][n];
         
        solve(grid, visited, 0, 0, 0);
        return globalmin;
    }

    public void solve(int[][] grid,boolean [][]visited ,int i, int j, int max){
        if(i==grid.length-1 && j==grid.length-1){
            if(max<grid[i][j]){
                max=grid[i][j];
            }
            globalmin=Math.min(max, globalmin);
            return;

        }
        
        if(i<0 || i>=grid.length || j<0 || j>=grid.length){
            return;
        }
        
        if(visited[i][j]) return;

        visited[i][j]=true;
        if(max<grid[i][j]){
            max=grid[i][j];
        }
        
        solve(grid, visited, i+1, j, max);
        solve(grid, visited, i-1, j, max);
        solve(grid, visited, i, j-1, max);
        solve(grid, visited, i, j+1, max);
        visited[i][j]=false;
    }
} //Gives TLE
*/

class Solution {
    public int swimInWater(int[][] grid) {
        int n=grid.length;
        int low=grid[0][0], high=n*n-1;
        int ans=0;

        while(low<=high){
            int mid=low+(high-low)/2;

            if(canreach(grid, mid, n)){
                ans=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return ans;
    }

    public boolean canreach(int [][]grid, int t, int n){
        if(grid[0][0]>t) return false;
        Queue<int []> q=new ArrayDeque<>();
        q.offer(new int[]{0,0});
        

        int [][]dirs={{-1,0}, {1,0}, {0,1}, {0,-1}};
        boolean visited[][]=new boolean[n][n];
        visited[0][0]=true;

        while(!q.isEmpty()){
            int curr[]=q.poll();
            if(curr[0]==n-1 && curr[1]==n-1) return true;

            for(int []dir : dirs){
                int r=curr[0]+dir[0];
                int c=curr[1]+dir[1];

                if(r>=0 && r<n && c>=0 && c<n && !visited[r][c] && grid[r][c]<=t){
                    visited[r][c]=true;
                    q.offer(new int[]{r,c});
                }
            }
        }
        return false;
    }
}

