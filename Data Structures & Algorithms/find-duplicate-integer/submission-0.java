class Solution {
    public int findDuplicate(int[] nums) {
        int i=0;
        while(nums[i]>0){
            int x=nums[i];
            nums[i]=nums[i]*-1;
            i=x;
        }
        return i;
    }
}
