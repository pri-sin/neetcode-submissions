class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length - 2; i++) {
            // FIX 1: Skip duplicate elements for 'i' to avoid duplicate triplets
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            int left = i + 1;
            int right = nums.length - 1;
            
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    
                    // FIX 2: Skip duplicates for 'left' and 'right'
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    
                    // FIX 3: Move the pointers inward after a match to avoid infinite loop
                    left++;
                    right--;
                    
                } else if (sum < 0) {
                    left++; // Sum is too small, make it bigger
                } else {
                    right--; // Sum is too big, make it smaller
                }
            }
        }
        return result;
    }
}