class Solution {
    public int maxProfit(int[] prices) {
        int maxprofit=0;
        int minprice=prices[0];
        for(int i=1;i<prices.length;i++){
            if(prices[i]>minprice){
                maxprofit=Math.max(maxprofit, prices[i]-minprice);
            }else{
                minprice=prices[i];
            }
        }
        return maxprofit;
    }
}
