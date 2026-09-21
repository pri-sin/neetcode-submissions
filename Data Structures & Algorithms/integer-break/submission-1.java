class Solution {
    public int integerBreak(int n) {
        Integer dp[][]=new Integer[n+1][n+1];
        return getResult(n, 1, dp);
    }

    public int getResult(int n, int i, Integer dp[][]){
        if(n==0){
            return 1;
        }

        if(i>=n){
            return n;
        }

        if(dp[n][i]!=null) return dp[n][i];

        int withbreak=i*getResult(n-i, i, dp);
        int nobreak=0;
        if(i+1<n){
            nobreak=getResult(n,i+1, dp);
        }

        return dp[n][i]=Math.max(withbreak, nobreak);
    }
}