class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Integer dp[][]=new Integer[m+1][n+1];
        return getResult(grid, 0, 0, dp);
    }

    public int getResult(int[][] grid, int i, int j, Integer dp[][]){
        if(i==grid.length-1 && j==grid[0].length-1){
            return grid[i][j];
        }

        if(i>=grid.length || j>=grid[0].length){
            return (int)1e9;
        }

        if(dp[i][j]!=null) return dp[i][j];

        int down=getResult(grid, i+1,j, dp);
        int right=getResult(grid, i, j+1, dp);

        return dp[i][j]=grid[i][j]+Math.min(down,right);
    }
}