class Solution {
    public int maxArea(int[] heights) {
        int left=0, right=heights.length-1;
        int maxwater=0;
        int lefth,righth;
        while(left<right){
            lefth=heights[left];
            righth=heights[right];
            int water=(Math.min(lefth, righth))*(right-left);
            maxwater=Math.max(water, maxwater);

            if(lefth<righth){
                left++;
            }else{
                right--;
            }
        }
        return maxwater;
    }
}