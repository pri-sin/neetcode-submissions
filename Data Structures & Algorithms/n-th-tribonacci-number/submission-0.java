class Solution {
    public int tribonacci(int n) {
        int dp[]=new int[n+1];
        return getResult(n, dp);
    }

    public int getResult(int n, int[]dp){
        if(n==0 || n==1){
            return n;
        }

        if(n==2){
            return 1;
        }

        if(dp[n]!=0) return dp[n];

        return dp[n]=getResult(n-1,dp)+getResult(n-2,dp)+getResult(n-3,dp);
    }
}