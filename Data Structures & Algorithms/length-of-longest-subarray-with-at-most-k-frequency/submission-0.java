class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        int left=0, right=0;
        int maxlength=0;

        Map<Integer, Integer> map=new HashMap<>();


        while(right<nums.length){
            map.put(nums[right], map.getOrDefault(nums[right],0)+1);

            while(map.get(nums[right])>k){
                map.put(nums[left], map.getOrDefault(nums[left],0)-1);
                left++;
            }

            maxlength=Math.max(maxlength, right-left+1);
            right++;
        }

        return maxlength;
    }
}
