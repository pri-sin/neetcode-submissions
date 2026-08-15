class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] counts = new int[26];
        for (char task : tasks) {
            counts[task - 'A']++;
        }

        // Max-Heap to prioritize tasks with the highest remaining frequency
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int count : counts) {
            if (count > 0) {
                maxHeap.add(count);
            }
        }

        // Queue to track tasks in cooldown: [remaining_count, available_at_time]
        Queue<int[]> cooldownQueue = new LinkedList<>();
        int time = 0;

        while (!maxHeap.isEmpty() || !cooldownQueue.isEmpty()) {
            time++;

            // Return task to heap if its cooldown period has ended
            if (!cooldownQueue.isEmpty() && cooldownQueue.peek()[1] == time) {
                maxHeap.add(cooldownQueue.poll()[0]);
            }

            // Execute the available task with the highest frequency
            if (!maxHeap.isEmpty()) {
                int remainingCount = maxHeap.poll() - 1;
                if (remainingCount > 0) {
                    // Task can be picked again at time + n + 1
                    cooldownQueue.add(new int[]{remainingCount, time + n + 1});
                }
            }
            // If maxHeap was empty, the CPU simply idles for this cycle
        }

        return time;
    }
}