/*class Solution {
    public int maxProfit(int[] prices) {
        int cp=Integer.MAX_VALUE;
        int n = prices.length;
        int maxprofit=0;
        return getProfit(prices, 0, Integer.MAX_VALUE);
    }

    public int getProfit(int []prices, int i, int cp){
        if(i>=prices.length){
            return 0;
        }

        int take=0,skip=0;
        if(prices[i]<cp){
            cp=prices[i];
            return getProfit(prices, i+1, cp);
        }

        if(cp<prices[i]){
            take=prices[i]-cp+getProfit(prices, i+2, Integer.MAX_VALUE);
            skip=getProfit(prices, i+1, cp);
        }

        return Math.max(take, skip);
    }
}//Gives TLE*/

class Solution {
    public int maxProfit(int[] prices) {
        int cp=Integer.MAX_VALUE;
        int n = prices.length;
        int maxprofit=0;
        Integer [][]memo=new Integer[n+1][n+1];
        return getProfit(prices, 0, -1, memo);
    }

    public int getProfit(int []prices, int i, int cp, Integer memo[][]){
        if(i>=prices.length){
            return 0;
        }

        if(cp!=-1 && memo[i][cp]!=null) return memo[i][cp];

        int take=0,skip=0;
        if(cp==-1 || prices[i]<prices[cp]){
            cp=i;
            return memo[i][cp]=getProfit(prices, i+1, cp, memo);
        }

        if(cp!=-1 && prices[cp]<=prices[i]){
            take=prices[i]-prices[cp]+getProfit(prices, i+2, -1, memo);
            skip=getProfit(prices, i+1, cp, memo);
        }

        return memo[i][cp]=Math.max(take, skip);
    }
}
