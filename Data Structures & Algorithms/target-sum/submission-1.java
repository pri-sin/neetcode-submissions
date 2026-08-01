class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int totalSum=0;
        for(int num:nums){
            totalSum+=num;
        }

        if(Math.abs(target)>totalSum || (totalSum+target)%2!=0) return 0;

        int targetsum=(totalSum+target)/2;

        Integer [][]memo=new Integer[nums.length][targetsum+1];

        return solve(nums, targetsum, 0, memo);
    }

    public int solve(int[] nums, int target, int i, Integer [][]memo){
        if(nums.length==i){
           return target==0?1:0;
        } 

        if(target<0) return 0;

        if(memo[i][target]!=null) return memo[i][target];

        int take=solve(nums, target-nums[i], i+1, memo);
        int skip=solve(nums, target, i+1, memo);

        return memo[i][target]=take+skip;
    }
}
