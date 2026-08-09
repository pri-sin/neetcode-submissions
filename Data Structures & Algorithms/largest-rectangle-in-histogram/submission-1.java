/*class Solution {
    public int largestRectangleArea(int[] heights) {
        int n=heights.length;
        int maxArea=0;
        for(int i=0;i<n;i++){
            int left=i, right=i;
            while(left>=0 && heights[i]<=heights[left]){
                    left--;
            }

            while(right<n && heights[i]<=heights[right]){
                    right++;
            }

            int area=heights[i]*(right-left-1);
            maxArea=Math.max(area, maxArea);
        }
        return maxArea;
    }
}//GIVES TLE
*/

class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int maxArea = 0;

        // Monotonic Increasing Stack storing indices of bars.
        // Using ArrayDeque instead of java.util.Stack for better performance.
        Deque<Integer> stack = new ArrayDeque<>();

        // Loop through indices 0 to n. 
        // The index 'n' acts as a virtual sentinel bar with height 0 to flush out remaining items.
        for (int i = 0; i <= n; i++) {
            // Assign height 0 at the virtual boundary index 'n'
            int currHeight = (i == n) ? 0 : heights[i];

            // Maintain monotonic property:
            // Whenever current bar is SHORTER than bar at stack top, 
            // the bar at stack top has reached its rightmost boundary.
            while (!stack.isEmpty() && heights[stack.peek()] > currHeight) {
                // Pop the bar that can no longer extend right
                int height = heights[stack.pop()];

                // Calculate width:
                // 1. If stack is empty, 'height' was the smallest element so far, 
                //    so its rectangle extends from index 0 all the way to index i - 1 (width = i).
                // 2. If stack is NOT empty, width spans from (stack.peek() + 1) to (i - 1).
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;

                // Update maximum area found so far
                maxArea = Math.max(maxArea, height * width);
            }

            // Push current bar's index onto the stack
            stack.push(i);
        }

        return maxArea;
    }
}