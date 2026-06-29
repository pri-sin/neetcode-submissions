class Solution {
    public int maxProfit(int[] prices) {
        int profit=0;
        int initial=prices[0];
        for(int i=1;i<prices.length;i++){
            if(prices[i]<initial){
                initial=prices[i];
            }else if(prices[i]>initial){
                if(profit<(prices[i]-initial)){
                    profit=prices[i]-initial;
                }
            }
        }
        return profit;
    }
}
