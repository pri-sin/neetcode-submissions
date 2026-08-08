/*class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int []res=new int[nums.length-k+1];

        int maxval=Integer.MIN_VALUE;
        PriorityQueue<Integer> pq=new PriorityQueue<>(Collections.reverseOrder());

        for(int i=0;i<k;i++){
            pq.offer(nums[i]);
        }
        res[0]=pq.peek();
        int i=1;
        int left=0,right=k;

        while(right<nums.length){
            pq.offer(nums[right]);
            pq.remove(nums[left]);
            res[i++]=pq.peek();
            left++;
            right++;
        }
        return res;
    }
}// Gives TLE
*/

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> dq=new ArrayDeque<>();

        int left=0,right=k;

        for(int i=0;i<k;i++){
            while(!dq.isEmpty() && dq.peekLast()<nums[i]){
                dq.pollLast();
            }
            dq.offerLast(nums[i]);
        }

        int res[]=new int[nums.length-k+1];
        res[0]=dq.peekFirst();
        int i=1;

        while(right<nums.length){
            while(!dq.isEmpty() && dq.peekLast()<nums[right]){
                dq.pollLast();
            }
            dq.offerLast(nums[right]);

            if(dq.peekFirst()==nums[left]){
                dq.pollFirst();
            }

            res[i++]=dq.peekFirst();
            right++;
            left++;
        }

        return res;
    }
}


/*
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        Deque<Integer> dq = new ArrayDeque<>(); // stores INDICES

        for (int right = 0; right < n; right++) {
            // 1. Remove indices that are out of the current window
            if (!dq.isEmpty() && dq.peekFirst() < right - k + 1) {
                dq.pollFirst();
            }

            // 2. Maintain decreasing order by removing smaller elements from the back
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[right]) {
                dq.pollLast();
            }

            // 3. Add current element's index
            dq.offerLast(right);

            // 4. Record maximum once the first full window (size k) is formed
            if (right >= k - 1) {
                res[right - k + 1] = nums[dq.peekFirst()];
            }
        }

        return res;
    }
}
*/