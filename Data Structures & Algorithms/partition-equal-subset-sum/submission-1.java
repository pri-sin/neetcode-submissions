class Solution {
    public boolean canPartition(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        // If total sum is odd, we cannot split it into two equal integer sums
        if (totalSum % 2 != 0) {
            return false;
        }

        int target = totalSum / 2;
        Boolean[][] memo = new Boolean[nums.length][target + 1];

        return solve(nums, 0, target, memo);
    }

    private boolean solve(int[] nums, int i, int target, Boolean[][] memo) {
        // Base Case 1: Exact target achieved
        if (target == 0) return true;

        // Base Case 2: Out of bounds or target went negative
        if (i >= nums.length || target < 0) return false;

        // Check Memo Cache
        if (memo[i][target] != null) return memo[i][target];

        // Choice 1: Take nums[i] -> move to i + 1
        boolean take = solve(nums, i + 1, target - nums[i], memo);

        // Choice 2: Skip nums[i] -> move to i + 1
        boolean skip = solve(nums, i + 1, target, memo);

        // Store and return boolean OR of both choices
        return memo[i][target] = take || skip;
    }
}
