/*class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a,b)->a[0]-b[0]);

        Stack<int[]> s=new Stack<>();
        for(int []interval:intervals){
            if(s.isEmpty() || s.peek()[1]<interval[0]){
                s.push(interval);
            }else if(s.peek()[1]>=interval[0]){
                int []curr=s.pop();
                int newint[]=new int[2];
                newint[0]=Math.min(curr[0],interval[0]);
                newint[1]=Math.max(curr[1],interval[1]);
                s.push(newint);
            }
        }
       return s.toArray(new int[0][]);
    }
}
*/

class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }

        // 1. Safe sort by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();

        for (int[] interval : intervals) {
            // If merged is empty or no overlap, add current interval
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
                merged.add(interval);
            } else {
                // Overlap exists: update the end time in-place
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], interval[1]);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }
}