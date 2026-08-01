class Solution {
    public int maxArea(int[] heights) {
        int left = 0;
        int right = heights.length - 1;
        int maxWater = 0;
        
        while (left < right) {
            // Calculate current area and update maxWater
            int currentWater = (right - left) * Math.min(heights[left], heights[right]);
            maxWater = Math.max(maxWater, currentWater);
            
            // Move the pointer pointing to the shorter line
            if (heights[left] < heights[right]) {
                left++;
            } else {
                right--;
            }
        }
        
        return maxWater;
    }
}