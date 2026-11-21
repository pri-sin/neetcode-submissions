/*class Solution {
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
}*/

class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int dp[]=new int[cost.length];
        Arrays.fill(dp,-1);
        return Math.min(getResult(cost,0,dp), getResult(cost,1,dp));
    }

    public int getResult(int []cost, int i, int []dp){
        if(i>=cost.length){
            return 0;
        }

        if(dp[i]!=-1) return dp[i];

        int take=getResult(cost, i+1, dp);
        int skip=getResult(cost,i+2, dp);

        return dp[i]=cost[i]+Math.min(take, skip);
    }
}
