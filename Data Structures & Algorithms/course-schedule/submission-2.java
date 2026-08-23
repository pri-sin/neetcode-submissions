class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Step 1: Build Adjacency List and Indegree Array
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        
        int[] indegree = new int[numCourses];

        // prerequisites[i] = [a, b] means b -> a (must take b before a)
        for (int[] req : prerequisites) {
            int course = req[0];
            int prereq = req[1];
            adj.get(prereq).add(course);
            indegree[course]++;
        }

        // Step 2: Push all courses with 0 prerequisites into the Queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        // Step 3: Process courses using BFS
        int completedCourses = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            completedCourses++;

            // Unlock downstream courses
            for (int neighbor : adj.get(current)) {
                indegree[neighbor]--;
                
                // If all prerequisites for neighbor are met
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // Step 4: If completed count equals numCourses, no cycle exists
        return completedCourses == numCourses;
    }
}