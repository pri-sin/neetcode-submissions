/*class Solution {
    public int subarraySum(int[] nums, int k) {
        int sum=0, totsum=0;
        int left=0, right=0;
        int count=0;
        while(right<nums.length){
            sum+=nums[right];
            totsum+=nums[right];
            if(totsum==k) count++;

            while(sum>=k){
                if(sum==k && totsum!=sum){
                    count++;
                }

                sum=sum-nums[left++];
            }
            right++;
        }

        return count;
    }
} 
//fails for nums=[-1,-1,1] ,k=0 because we are not considering cases where in left could move even when sum<k
*/

class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixCounts = new HashMap<>();
        // Base case: a prefix sum of 0 has occurred once (represents an empty subarray)
        prefixCounts.put(0, 1);

        int currentSum = 0;
        int count = 0;

        for (int num : nums) {
            currentSum += num;

            // Check if there is a prefix sum that satisfies: currentSum - prefix = k
            if (prefixCounts.containsKey(currentSum - k)) {
                count += prefixCounts.get(currentSum - k);
            }

            // Record the current prefix sum in the map
            prefixCounts.put(currentSum, prefixCounts.getOrDefault(currentSum, 0) + 1);
        }

        return count;
    }
}