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


/*
class Solution {
    public long countSubarrays(int[] nums, int k) {
        // Step 1: Find the maximum element in nums
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }

        // Step 2: Sliding window to count subarrays
        int left = 0;
        int count = 0;
        long ans = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == max) {
                count++;
            }

            // Shrink from the left while the window has at least k occurrences
            while (count >= k) {
                if (nums[left] == max) {
                    count--;
                }
                left++;
            }

            // Every start index from 0 to left - 1 forms a valid subarray ending at right
            ans += left;
        }

        return ans;
    }
}
*/