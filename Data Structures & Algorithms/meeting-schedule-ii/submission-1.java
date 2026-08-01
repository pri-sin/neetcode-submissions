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