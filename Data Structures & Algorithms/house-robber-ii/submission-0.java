class Solution {
    public int rob(int[] nums) {
        if(nums==null || nums.length==0){
            return 0;
        }

        if(nums.length==1){
            return nums[0];
        }

        int rob1=robLinear(nums, 0,nums.length-2);
        int rob2=robLinear(nums, 1,nums.length-1);

        return Math.max(rob1, rob2);
    }

    public int robLinear(int []nums, int start, int end){
        int prevmax=0, currmax=0;

        for(int i=start;i<=end;i++){
            int temp=Math.max(currmax, prevmax+nums[i]);
            prevmax=currmax;
            currmax=temp;
        }

        return currmax;
    }
}
