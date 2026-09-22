class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int left=0;
        int right=0;
        int minlen=Integer.MAX_VALUE;
        int sum=0;
        while(right<nums.length){
            sum+=nums[right];

            while(sum>=target){
                minlen=Math.min(minlen, right-left+1);
                sum-=nums[left];
                left++;
            }
            right++;
        }
        return minlen==Integer.MAX_VALUE?0:minlen;
    }
}