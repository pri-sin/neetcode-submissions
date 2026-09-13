class Solution {
    public int removeElement(int[] nums, int val) {
        int left=0;

        for(int i=0;i<nums.length;i++){
            if(nums[i]!=val){
                int temp=nums[left];
                nums[left]=nums[i];
                nums[i]=temp;
                left++;
            }
        }
        return left;
    }
}