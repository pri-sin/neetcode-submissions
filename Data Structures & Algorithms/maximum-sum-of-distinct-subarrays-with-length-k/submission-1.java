class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        int left=0, right=0;
        long sum=0, maxsum=0;
        //List<Integer> list=new ArrayList<>();
        //Switch ArrayList to a HashSet<Integer>. Hash set lookups, insertions, and deletions run in $O(1)$ average time, bringing the overall time complexity down to $O(n)$.
        Set<Integer> list=new HashSet<>();
        while(right<nums.length){
            while(list.contains(nums[right])){
                sum=sum-nums[left];
                list.remove(nums[left]);
                left++;
            }
            sum+=nums[right];
            list.add(nums[right]);
        

            if(right-left+1==k){
                maxsum=Math.max(sum, maxsum);
                sum=sum-nums[left];
                list.remove(Integer.valueOf(nums[left]));
                left++;
            }
            right++;
        }
        return maxsum;
    }
}