class Solution {
    public int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);
        int dp[]=new int[target+1];
        Arrays.fill(dp,-1);
        return getResult(nums,target,dp);
    }

    public int getResult(int[] nums, int target, int []dp){

        if(target<0){
            return 0;
        }

        if(target==0){
            return 1;
        }

        if(dp[target]!=-1) return dp[target];
        int take=0, skip=0;

        for(int i=0;i<nums.length;i++){
            if(target-nums[i]>=0){
                take+=getResult(nums, target-nums[i], dp);
            }
            //skip+=getResult(nums, target, i+1); //not required
        }
        return dp[target]=take+skip;
    }
}