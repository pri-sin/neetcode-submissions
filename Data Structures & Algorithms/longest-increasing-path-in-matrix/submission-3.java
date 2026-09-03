/*class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        int m =matrix.length;
        int n =matrix[0].length;
        int maxcount=0;

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i>0 && i<m-1){
                    if(matrix[i-1][j]<matrix[i][j] ||
                       matrix[i+1][j]<matrix[i][j]){
                            continue;
                       }
                }
                if(j>0 && j<n-1){
                    if(matrix[i][j-1]<matrix[i][j] ||
                       matrix[i][j+1]<matrix[i][j] ){
                            continue;
                    }
                }
                
                maxcount=Math.max(maxcount,getPath(matrix, i, j, -1));
            }
        }
        return maxcount;
    }

    public int getPath(int[][] matrix, int i, int j, int last){
        if(i<0 || i>=matrix.length ||j<0 || j>=matrix[0].length){
            return 0;
        }

        int count=0;
        if(matrix[i][j]>last){
            int goleft=getPath(matrix, i,j-1,matrix[i][j]);
            int goright=getPath(matrix, i,j+1,matrix[i][j]);
            int goup=getPath(matrix, i-1,j,matrix[i][j]);
            int godown=getPath(matrix, i+1,j,matrix[i][j]);
            count=1+Math.max(Math.max(goleft, goright), Math.max(goup,godown));
        }

        return count;
    }
}*/// Gives TLE

/*class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int maxcount = 0;
        int[][] memo = new int[m][n]; // Memoization array

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxcount = Math.max(maxcount, getPath(matrix, i, j, -1, memo));
            }
        }
        return maxcount;
    }

    public int getPath(int[][] matrix, int i, int j, int last, int[][] memo) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length) {
            return 0;
        }

        if (matrix[i][j] <= last) {
            return 0;
        }

        // Return cached result if this cell's longest path was already computed
        if (memo[i][j] != 0) {
            return memo[i][j];
        }

        int goleft = getPath(matrix, i, j - 1, matrix[i][j], memo);
        int goright = getPath(matrix, i, j + 1, matrix[i][j], memo);
        int goup = getPath(matrix, i - 1, j, matrix[i][j], memo);
        int godown = getPath(matrix, i + 1, j, matrix[i][j], memo);

        int count = 1 + Math.max(Math.max(goleft, goright), Math.max(goup, godown));

        // Store result in memo before returning
        return memo[i][j] = count;
    }
}*/

class Solution {
    private static final int[][] DIRS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        int[][] memo = new int[m][n];
        int maxPath = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxPath = Math.max(maxPath, dfs(matrix, i, j, memo));
            }
        }

        return maxPath;
    }

    private int dfs(int[][] matrix, int i, int j, int[][] memo) {
        // Return cached result immediately
        if (memo[i][j] != 0) {
            return memo[i][j];
        }

        int maxLen = 1;

        for (int[] dir : DIRS) {
            int ni = i + dir[0];
            int nj = j + dir[1];

            // Pre-check validity BEFORE recursing to preserve stack space
            if (ni >= 0 && ni < matrix.length && nj >= 0 && nj < matrix[0].length 
                && matrix[ni][nj] > matrix[i][j]) {
                maxLen = Math.max(maxLen, 1 + dfs(matrix, ni, nj, memo));
            }
        }

        return memo[i][j] = maxLen;
    }
}

/*
class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int maxcount = 0;
        int[][] memo = new int[m][n]; // 1. Memoization table

        // 2. Simply check every cell without manual 'continue' filters
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxcount = Math.max(maxcount, getPath(matrix, i, j, memo));
            }
        }
        return maxcount;
    }

    public int getPath(int[][] matrix, int i, int j, int[][] memo) {
        // Return cached result if already calculated
        if (memo[i][j] != 0) {
            return memo[i][j];
        }

        int goleft = 0, goright = 0, goup = 0, godown = 0;

        // 3. Pre-check neighbor bounds & value before recursing to save stack space
        if (j > 0 && matrix[i][j - 1] > matrix[i][j]) {
            goleft = getPath(matrix, i, j - 1, memo);
        }
        if (j < matrix[0].length - 1 && matrix[i][j + 1] > matrix[i][j]) {
            goright = getPath(matrix, i, j + 1, memo);
        }
        if (i > 0 && matrix[i - 1][j] > matrix[i][j]) {
            goup = getPath(matrix, i - 1, j, memo);
        }
        if (i < matrix.length - 1 && matrix[i + 1][j] > matrix[i][j]) {
            godown = getPath(matrix, i + 1, j, memo);
        }

        int maxNeighbor = Math.max(Math.max(goleft, goright), Math.max(goup, godown));
        
        // Cache and return
        return memo[i][j] = 1 + maxNeighbor;
    }
}*/
