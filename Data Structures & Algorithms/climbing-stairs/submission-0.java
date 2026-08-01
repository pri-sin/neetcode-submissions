class Solution {
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
}