/*class Solution {
    public int lengthOfLIS(int[] nums) {
        return solve(nums, 0, -1);
    }

    private int solve(int[] nums, int curr, int prev) {
        // Base case: processed all elements
        if (curr == nums.length) {
            return 0;
        }

        // Option 1: Skip the current element
        int skip = solve(nums, curr + 1, prev);

        // Option 2: Take the current element (if valid)
        int take = 0;
        if (prev == -1 || nums[curr] > nums[prev]) {
            take = 1 + solve(nums, curr + 1, curr);
        }

        return Math.max(take, skip);
    }
}*/

class Solution {
    public int lengthOfLIS(int[] nums) {
        int dp[]=new int[nums.length];
        Arrays.fill(dp,1);
        int maxLen=1;

        for(int i=1;i<nums.length;i++){
            for(int j=0;j<i;j++){
                if(nums[i]>nums[j]){
                    dp[i]=Math.max(dp[i], dp[j]+1);
                }
            }
            maxLen=Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }

    
}
