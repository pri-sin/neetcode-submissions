class Solution {
    public int maxSubArray(int[] nums) {
        int maxsum=Integer.MIN_VALUE;
        int n = nums.length;
        int sum=(int)-1e9;
        for(int i=0;i<n; i++){
            sum=Math.max(sum+nums[i], nums[i]);
            maxsum=Math.max(sum,maxsum);
        }
        return maxsum;
    }
}
