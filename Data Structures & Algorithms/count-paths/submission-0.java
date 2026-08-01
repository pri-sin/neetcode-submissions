class Solution {
    public int uniquePaths(int m, int n) {
        int memo[][]=new int[m][n];
        return dfs(m,n,0,0, memo);
    }

    public int dfs(int m,int n,int i,int j, int memo[][]){
        if(i==m-1 && j==n-1){
            return 1;
        }

        if(i>m-1 || j>n-1) return 0;

        if(memo[i][j]!=0) return memo[i][j];

        int right=dfs(m,n,i,j+1,memo);
        int down=dfs(m,n,i+1,j, memo);

        return memo[i][j]=right+down; 
    }
}
