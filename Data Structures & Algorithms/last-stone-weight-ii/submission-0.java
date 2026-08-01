class Solution {
    public int lastStoneWeightII(int[] stones) {
        // Step 1: Calculate total sum of all stone weights
        int totalSum = 0;
        for (int stone : stones) {
            totalSum += stone;
        }

        // Step 2: Set target capacity to half of the total sum.
        // Goal: Find a subset S1 with total weight as close to totalSum / 2 as possible.
        int target = totalSum / 2;

        // Step 3: Memoization table [index][remaining_target]
        // Stores the maximum weight achievable starting from 'index' with capacity 'target'
        Integer[][] memo = new Integer[stones.length][target + 1];

        // Step 4: Find the maximum weight S1 <= totalSum / 2
        int maxSubsetSum = maxWeight(stones, 0, target, memo);

        // Step 5: Answer = S2 - S1 = (totalSum - S1) - S1 = totalSum - 2 * S1
        return totalSum - 2 * maxSubsetSum;
    }

    private int maxWeight(int[] stones, int index, int target, Integer[][] memo) {
        // Base Case: Out of stones OR reached maximum weight capacity
        if (index == stones.length || target == 0) {
            return 0;
        }

        // Return cached result if already computed
        if (memo[index][target] != null) {
            return memo[index][target];
        }

        // Option 1: SKIP current stone
        int skip = maxWeight(stones, index + 1, target, memo);

        // Option 2: TAKE current stone (only if stone weight fits in capacity)
        int take = 0;
        if (stones[index] <= target) {
            take = stones[index] + maxWeight(stones, index + 1, target - stones[index], memo);
        }

        // Return and store the maximum achievable weight between taking or skipping
        return memo[index][target] = Math.max(take, skip);
    }
}