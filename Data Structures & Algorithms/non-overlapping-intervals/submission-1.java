class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a,b)->Integer.compare(a[1],b[1]));
        int last=0;
        int count=0;
        for(int i=1;i<intervals.length;i++){
            if(intervals[last][1]>intervals[i][0]){
                count++;
            }else{
                last=i;
            }
        }
        return count;
    }
}
/*
import java.util.Arrays;

class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        
        // Sort primarily by end time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        
        int count = 0;
        int lastEnd = intervals[0][1];
        
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < lastEnd) {
                // Overlap found: remove current interval (keep the one ending earlier)
                count++;
            } else {
                // No overlap: update the end boundary
                lastEnd = intervals[i][1];
            }
        }
        
        return count;
    }
}

Consider the test case: intervals = [[1, 10], [2, 3], [3, 4]].

Sorting: Sorting by start time yields [[1, 10], [2, 3], [3, 4]]. Adding a secondary sort by end time changes nothing here because all start times are distinct.

Iteration Step 1 (i = 1): Comparing last ([1, 10]) with i ([2, 3]). Since 10 > 2, an overlap is detected, count increments, and last remains 0 ([1, 10]).

Iteration Step 2 (i = 2): Comparing last ([1, 10]) with i ([3, 4]). Since 10 > 3, an overlap is detected again, and count increments.

Result: The code counts 2 removals. However, removing only [1, 10] leaves [2, 3] and [3, 4] non-overlapping, requiring only 1 removal.

Why Start-Time Sorting Fails Here

When two intervals overlap, the greedy choice is to keep the interval that finishes earlier, because it leaves the maximum room for remaining intervals.

If you sort by start time and keep last unchanged during an overlap, a long early interval (like [1, 10]) will continuously conflict with shorter, valid intervals that follow it. Updating secondary sorting for equal start times will not fix cases where start times differ.*/