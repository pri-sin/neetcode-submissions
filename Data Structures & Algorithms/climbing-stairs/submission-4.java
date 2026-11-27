/*class Solution {
    public int climbStairs(int n) {
        int dp[] = new int[n + 1];
        return dynamo(dp, n);
    }

    public int dynamo(int[] dp, int i) {
        // Base cases: 
        if (i < 0) return 0; // Out of bounds prevention
        if (i == 0 || i == 1) return 1; // 1 way to stay at step 0 or step 1
        
        // If already calculated, return the cached value
        if (dp[i] != 0) {
            return dp[i];
        }

        // Memoize the result
        dp[i] = dynamo(dp, i - 1) + dynamo(dp, i - 2); 
        return dp[i];
    }
}*/
/*
class Solution {

    public int climbStairs(int n) {
        int dp[] = new int[n + 1];
        return dynamo(dp, n);
    }

    public int dynamo(int[] dp, int i) {
        // Base cases: 
        if (i < 0) return 0; 
        if (i == 0 ) return 1; 
        
        // If already calculated, return the cached value
        if (dp[i] != 0) {
            return dp[i];
        }

        // Memoize the result
        dp[i] = dynamo(dp, i - 1) + dynamo(dp, i - 2); 
        return dp[i];
    }
}*/

class Solution {
    public int climbStairs(int n) {
        int dp[]=new int[n];
        return getResult(n,0, dp);
    }

    public int getResult(int n, int i, int []dp){
        if(i==n){
            return 1;
        }

        if(i>n) return 0;

        if(dp[i]!=0) return dp[i];

        int take1=getResult(n, i+1, dp);
        int take2=getResult(n, i+2, dp);

        return dp[i]=take1+take2;
    }
}