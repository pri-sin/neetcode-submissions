class Solution {
    public int rob(int[] nums) {
        Integer []dp=new Integer[nums.length+1];
        return Math.max(getResult(nums, 0, dp), getResult(nums,1, dp));
    }

    public int getResult(int []nums, int i, Integer dp[]){
        if(i>=nums.length){
            return 0;
        }

        if(dp[i]!=null) return dp[i];
        int take=nums[i]+getResult(nums, i+2, dp);
        int skip=getResult(nums, i+1, dp);

        return dp[i]=Math.max(take, skip);
    }
}// gives TLE without DP.

/*class Solution {
    public int rob(int[] nums) {
         if(nums==null || nums.length==0){
            return 0;
         }

         int prevmax=0, currmax=0;

         for(int num:nums){
            int temp=Math.max(currmax, prevmax+num);
            prevmax=currmax;
            currmax=temp;
         }

         return currmax;
    }
}
*/