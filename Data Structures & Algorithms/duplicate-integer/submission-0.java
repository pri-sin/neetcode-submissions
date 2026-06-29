class Solution {
    public boolean hasDuplicate(int[] nums) {
        List<Integer> list=new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            if(list.contains(nums[i])){
                return true;
            }
            list.add(nums[i]);
        }
        return false;
    }
}