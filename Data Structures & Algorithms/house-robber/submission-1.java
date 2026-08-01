class Solution {
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
