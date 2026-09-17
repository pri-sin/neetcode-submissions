class Solution {
    public int maxProfit(int[] prices) {
        int n=prices.length;
        Integer [][]dp=new Integer[n][n];
        return getProfit(prices, 0, 0, dp);
    }

    public int getProfit(int []prices, int i, int cp, Integer [][]dp){
        if(i==prices.length){
            return 0;
        }

        if(dp[i][cp]!=null) return dp[i][cp];
        int take=0, skip=0;
        if(prices[i]>prices[cp]){
            take=(prices[i])-prices[cp]+getProfit(prices, i+1, i, dp);
            skip=getProfit(prices, i+1, cp, dp);
        }else{
            skip=getProfit(prices, i+1, i, dp);
        }

        return dp[i][cp]=Math.max(take, skip);
    }
}