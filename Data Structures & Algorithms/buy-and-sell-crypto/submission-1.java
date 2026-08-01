class Solution {
    public int maxProfit(int[] prices) {
        int maxprofit=0;
        int curr=prices[0];
        for(int i=1;i<prices.length;i++){
            if(curr<prices[i]){
                maxprofit=Math.max(maxprofit, prices[i]-curr);
            }else{
                curr=prices[i];
            }
        }
        return maxprofit;
    }
}
