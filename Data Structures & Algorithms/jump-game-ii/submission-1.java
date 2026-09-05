/*class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        Integer memo[]=new Integer[n+1];
        return getCount(nums, 0, memo);
    }

    public int getCount(int []nums,int i, Integer memo[]){
        if(i>=nums.length-1){
            return 0;
        }

        if(memo[i]!=null) return memo[i];

        int count=Integer.MAX_VALUE;
        for(int j=nums[i];j>0;j--){
            int x=getCount(nums, i+j, memo);
            if(x==Integer.MAX_VALUE) continue;
            count=Math.min(count, 1+x);
        }
        return memo[i]=count;
    }
} //Will give TLE for large nos
*/

class Solution {
    public int jump(int[] nums) {
        int maxReach = 0;
        int currentEnd = 0;
        int stepCount = 0;

        // Stop before the last index so you don't jump off the end
        for (int i = 0; i < nums.length - 1; i++) {
            maxReach = Math.max(maxReach, i + nums[i]);

            if (i == currentEnd) {
                stepCount++;
                currentEnd = maxReach; // Set end boundary to the maximum reach found so far
            }
        }
        return stepCount;
    }
}