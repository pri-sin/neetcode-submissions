/**
 * Definition of Interval:
 * public class Interval {
 *     public int start, end;
 *     public Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

class Solution {
    public int minMeetingRooms(List<Interval> intervals) {
        // Edge case: Empty input
        if (intervals == null || intervals.isEmpty()) {
            return 0;
        }

        // 1. Sort meetings by start time (using Integer.compare to prevent overflow)
        Collections.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));

        // 2. Min-Heap storing end times of active meetings
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 3. Add the end time of the earliest starting meeting
        pq.add(intervals.get(0).end);

        for (int i = 1; i < intervals.size(); i++) {
            Interval current = intervals.get(i);

            // If the earliest finishing meeting has ended before/at current start time, reuse the room
            if (current.start >= pq.peek()) {
                pq.poll();
            }

            // Allocate a room (either recycled or new) by pushing the current end time
            pq.add(current.end);
        }

        // The size of the heap represents the peak number of simultaneous rooms needed
        return pq.size();
    }
}

/*
class Solution {
    public int minMeetingRooms(int[] start, int[] end) {
        // code here
        int [][]meetings=new int[start.length][2];
        
        for(int i=0;i<start.length;i++){
            meetings[i][0]=start[i];
            meetings[i][1]=end[i];
        }
        
        Arrays.sort(meetings, (a,b)->a[0]-b[0]);
        int count=0;
        PriorityQueue<int []> pq=new PriorityQueue<>((a,b)->a[1]-b[1]);
        
        for(int i=0;i<start.length;i++){
            if(pq.size()==0 || pq.peek()[1]>meetings[i][0]){
                count++;
            }else{
                pq.poll();
            }
            pq.offer(meetings[i]);
        }
        return count;
    }
}
*/