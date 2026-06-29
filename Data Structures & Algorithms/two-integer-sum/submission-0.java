class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> list=new HashMap<>();
        int []ans= new int[2];
        for(int i=0;i<nums.length;i++){
            if(list.containsKey(target-nums[i])){
                ans[0]=list.get(target-nums[i]);
                ans[1]=i;
            }
            list.put(nums[i],i);
        }
        return ans;
    }
}
