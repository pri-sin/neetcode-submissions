/*class Solution {
    public void islandsAndTreasure(int[][] grid) {
        int m=grid.length;
        int n=grid[0].length;

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==0){
                    solve(grid, i, j, 0);
                }
            }
        }
    }

    public void solve(int [][]grid, int i, int j, int count){
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length || grid[i][j]<0){
            return;
        }

        if(grid[i][j]>=count){
            grid[i][j]=count;
        }else{
            count=0;//Note on your code: Setting count = 0 inside the else block has no effect because Java passes primitive types by value. The return; statement is doing all the work by pruning the dead branch.
            return;
        }

        solve(grid, i+1, j, count+1);
        solve(grid, i-1, j, count+1);
        solve(grid, i, j+1, count+1);
        solve(grid, i, j-1, count+1);

    }
}*///Gives TLE
class Solution {
    public void islandsAndTreasure(int[][] grid) {
        if (grid == null || grid.length == 0) return;

        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new ArrayDeque<>();

        // 1. Enqueue all treasure chests (0)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        // 2. Expand outward from all sources simultaneously
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];

            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                // Check bounds and ensure target is an unvisited empty land cell (INF)
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] == Integer.MAX_VALUE) {
                    grid[nr][nc] = grid[r][c] + 1;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
    }
}