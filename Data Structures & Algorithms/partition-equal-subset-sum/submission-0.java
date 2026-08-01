class Solution {
    public boolean canPartition(int[] nums) {
        int totalsum=0;

        for(int i=0;i<nums.length;i++){
            totalsum+=nums[i];
        }

        if(totalsum%2!=0) return false;

        int target=totalsum/2;
        Boolean memo[][]=new Boolean[nums.length][target+1];
        
        return subsetsum(nums, target, 0, memo);
    }

    public boolean subsetsum(int[] nums, int target, int i, Boolean [][]memo){
        if(target==0) return true;

        if(i>=nums.length || target<0) return false;

        if(memo[i][target]!=null) return memo[i][target];

        boolean take=subsetsum(nums, target-nums[i], i+1,memo);
        boolean skip=subsetsum(nums, target, i+1, memo);

        return memo[i][target]=take||skip;
    }
}
