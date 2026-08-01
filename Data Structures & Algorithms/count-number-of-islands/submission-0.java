class Solution {
    public int numIslands(char[][] grid) {
        int count=0;
        int m=grid.length;
        int n=grid[0].length;
        boolean visited[][]=new boolean[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]=='1' && !visited[i][j]){
                    dfs(grid, i, j, visited);
                    count++;
                }
            }
        }
        return count;
    }

    public void dfs(char[][] grid, int i, int j, boolean visited[][]){
        boolean rowbounds = i>=0 && i<grid.length;
        boolean colbounds = j>=0 && j<grid[0].length;

        if(!rowbounds || !colbounds) return;
        if(grid[i][j]=='0') return;
        if(visited[i][j]) return;

        visited[i][j]=true;
        dfs(grid, i-1, j, visited);
        dfs(grid, i+1, j, visited);
        dfs(grid, i, j-1, visited);
        dfs(grid, i, j+1, visited);
        
    }

}
