class Solution {
    public int numIslands(char[][] grid) {
        int m=grid.length;
        int n=grid[0].length;

        int numislands=0;

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]=='1'){
                    numislands++;
                    dfs(grid, i, j, m, n);
                }
            }
        }
        return numislands;
    }

    public void dfs(char[][] grid, int i, int j, int m, int n){
        if(i<0 || i>=m ||j<0 ||j>=n || grid[i][j]=='0'){
            return;
        }

        grid[i][j]='0';

        int dirs[][]={{-1,0}, {1,0}, {0,1}, {0,-1}};

        for(int []dir:dirs){
            int r=dir[0]+i;
            int c=dir[1]+j;
            dfs(grid, r, c,m,n);
        }
    }
}

/*class Solution {
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

}*/
