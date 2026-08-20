class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int maxcount=0;

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                int count=0;
                if(grid[i][j]!=0){
                    count=area(grid, i, j);
                }
                maxcount=Math.max(count, maxcount);
            }
        }
        return maxcount;
    }

    public int area(int[][] grid, int i, int j){
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length || grid[i][j]==0){
            return 0;
        }

        grid[i][j]=0;

        int count = 1+area(grid, i+1,j)+area(grid, i-1,j)+area(grid, i,j+1)+area(grid, i,j-1);
        
        return count;
    }
}
