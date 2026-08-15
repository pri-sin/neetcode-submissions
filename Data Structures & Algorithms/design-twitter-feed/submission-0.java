
class Twitter {
    private static int timeStamp = 0;

    private class Tweet {
        int id;
        int time;
        Tweet next;

        Tweet(int id) {
            this.id = id;
            this.time = timeStamp++;
            this.next = null;
        }
    }

    private Map<Integer, Set<Integer>> followMap;
    private Map<Integer, Tweet> tweetMap;

    public Twitter() {
        followMap = new HashMap<>();
        tweetMap = new HashMap<>();
    }
    
    public void postTweet(int userId, int tweetId) {
        Tweet newTweet = new Tweet(tweetId);
        // Prepend new tweet to the head of user's linked list
        newTweet.next = tweetMap.get(userId);
        tweetMap.put(userId, newTweet);
    }
    
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new ArrayList<>();
        // Max-heap ordered by most recent timestamp
        PriorityQueue<Tweet> pq = new PriorityQueue<>((a, b) -> b.time - a.time);

        // Include user's own most recent tweet
        if (tweetMap.containsKey(userId)) {
            pq.offer(tweetMap.get(userId));
        }

        // Include followees' most recent tweets
        Set<Integer> followings = followMap.getOrDefault(userId, Collections.emptySet());
        for (int followeeId : followings) {
            if (tweetMap.containsKey(followeeId)) {
                pq.offer(tweetMap.get(followeeId));
            }
        }

        // K-way merge: extract top 10 most recent tweets overall
        while (!pq.isEmpty() && res.size() < 10) {
            Tweet curr = pq.poll();
            res.add(curr.id);
            if (curr.next != null) {
                pq.offer(curr.next);
            }
        }

        return res;
    }
    
    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) return;
        followMap.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
    }
    
    public void unfollow(int followerId, int followeeId) {
        if (followMap.containsKey(followerId)) {
            followMap.get(followerId).remove(followeeId);
        }
    }
}