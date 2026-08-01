class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int []dp=new int[cost.length+1];
        Arrays.fill(dp, -1);
        return Math.min(dynamo(cost, dp, 0), dynamo(cost,dp,1));
    }

    public int dynamo(int []cost, int []dp, int i){
        if(i>=cost.length) return 0;
        

        if(dp[i]!=-1) {
            return dp[i];
        } 

        dp[i]=cost[i]+Math.min(dynamo(cost,dp,i+1), dynamo(cost,dp,i+2));
        return dp[i];
    }
}
