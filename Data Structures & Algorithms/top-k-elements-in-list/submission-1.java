
public class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // Step 1: Count the frequency of each element
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Create buckets where index represents the frequency
        // Size is nums.length + 1 to account for index equal to nums.length
        List<Integer>[] bucket = new List[nums.length + 1];
        
        for (int key : countMap.keySet()) {
            int frequency = countMap.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        // Step 3: Accumulate the top k frequent elements from right to left
        int[] result = new int[k];
        int index = 0;

        for (int pos = bucket.length - 1; pos >= 0 && index < k; pos--) {
            if (bucket[pos] != null) {
                for (int num : bucket[pos]) {
                    result[index++] = num;
                    if (index == k) {
                        return result;
                    }
                }
            }
        }
        
        return result;
    }
}