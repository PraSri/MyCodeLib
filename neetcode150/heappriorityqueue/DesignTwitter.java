package heappriorityqueue;

import java.util.*;

// Design a File Sharing System - https://leetcode.com/problems/design-a-file-sharing-system/

public class DesignTwitter {

    static class Twitter {

        // entities - user, tweet

        private static int timestamp = 0;
        private final Map<Integer, User> userMap;

        static class User {

            public int id;
            public Set<Integer> followed;
            public Tweet tweetHead;

            public User(int id) {
                this.id = id;
                followed = new HashSet<>();
                follow(id);
                this.tweetHead = null;
            }

            public void follow(int id) {
                followed.add(id);
            }

            public void unfollow(int id) {
                followed.remove(id);
            }

            public void post(int id) {
                Tweet tweet = new Tweet(id);
                tweet.next = tweetHead;
                tweetHead = tweet;
            }

        }

        static class Tweet {
            public int id;
            public int time;
            public Tweet next;

            public Tweet(int id) {
                this.id = id;
                this.time = timestamp++;
                next = null;
            }
        }

        public Twitter() {
            this.userMap = new HashMap<>();
        }

        public void postTweet(int userId, int tweetId) {

            if (!userMap.containsKey(userId)) {
                User user = new User(userId);
                userMap.put(userId, user);
            }
            userMap.get(userId).post(tweetId);

        }

        public List<Integer> getNewsFeed(int userId) {
            List<Integer> res = new ArrayList<>();
            if (!userMap.containsKey(userId))
                return res;
            // get all the users followed
            Set<Integer> users = userMap.get(userId).followed;
            PriorityQueue<Tweet> q = new PriorityQueue<>(users.size(), (a, b) -> b.time - a.time);
            // putting all tweet heads in priority queue, kind of merge k linked list
            for (int user : users) {
                Tweet t = userMap.get(user).tweetHead;
                if (t != null) {
                    q.add(t);
                }
            }
            int n = 0;
            while (!q.isEmpty() && n < 10) {
                Tweet t = q.poll();
                res.add(t.id);
                n++;
                if (t.next != null) {
                    q.add(t.next);
                }
            }
            return res;
        }

        public void follow(int followerId, int followeeId) {
            if (!userMap.containsKey(followerId)) {
                User user = new User(followerId);
                userMap.put(followerId, user);
            }
            if (!userMap.containsKey(followeeId)) {
                User user = new User(followeeId);
                userMap.put(followeeId, user);
            }
            userMap.get(followerId).follow(followeeId);
        }

        public void unfollow(int followerId, int followeeId) {
            if (!userMap.containsKey(followerId) || followerId == followeeId) {
                return;
            }
            userMap.get(followerId).unfollow(followeeId);
        }
    }

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */

    /**
     * Design a File Sharing System
     * https://leetcode.com/problems/design-a-file-sharing-system/
     */
    public static class DesignAFileSharingSystem {
    }
}
