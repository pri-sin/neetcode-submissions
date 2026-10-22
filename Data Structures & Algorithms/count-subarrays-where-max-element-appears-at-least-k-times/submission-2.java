class Solution {
    public long countSubarrays(int[] nums, int k) {
        int max=0, count=0;
        for (int num : nums) {
            max = Math.max(max, num);
        }

        int left=0, right=0;
        long ans=0;

        while(right<nums.length){
            if(nums[right]==max){
                count++;
            }

            while(count>=k){

                ans+=nums.length-1-right+1;
                if(nums[left]==max) count--;
                left++;
            }
            right++;
        }

        return ans;
    }
}
