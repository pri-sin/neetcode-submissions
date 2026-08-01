class Solution {
    public int numSquares(int n) {
        if(n==0) return 0;
        int x=(int)Math.sqrt(n);
        Integer memo[][]=new Integer[x+1][n+1];
        return solve(n, x, memo);
    }

    public int solve(int n,int i, Integer memo[][]){
        if(n==0) return 0;

        if(i==0 || n<0) return (int)1e9;

        if(memo[i][n]!=null) return memo[i][n];

        int take=1+solve(n-i*i, i, memo);
        int skip=solve(n,i-1, memo);

        return  memo[i][n]=Math.min(take, skip);
    }
}