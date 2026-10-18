package k_way_merge.design_twitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Solution {

    public static class Twitter {
        private static final int FEED_SIZE = 10;

        private final Map<Integer, List<int[]>> tweets = new HashMap<>(); // userId -> [{time, tweetId}]
        private final Map<Integer, Set<Integer>> following = new HashMap<>();
        private int clock = 0;

        public Twitter() {
        }

        public void postTweet(int userId, int tweetId) {
            tweets.computeIfAbsent(userId, k -> new ArrayList<>()).add(new int[]{clock++, tweetId});
        }

        public List<Integer> getNewsFeed(int userId) {
            // heap entry: {time, tweetId, userId, indexInUserList}
            PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0]));
            Set<Integer> sources = new HashSet<>(following.getOrDefault(userId, Set.of()));
            sources.add(userId);
            for (int user : sources) {
                List<int[]> list = tweets.get(user);
                if (list != null && !list.isEmpty()) {
                    int last = list.size() - 1;
                    heap.offer(new int[]{list.get(last)[0], list.get(last)[1], user, last});
                }
            }
            List<Integer> feed = new ArrayList<>(FEED_SIZE);
            while (!heap.isEmpty() && feed.size() < FEED_SIZE) {
                int[] top = heap.poll();
                feed.add(top[1]);
                int prevIdx = top[3] - 1;
                if (prevIdx >= 0) {
                    int[] older = tweets.get(top[2]).get(prevIdx);
                    heap.offer(new int[]{older[0], older[1], top[2], prevIdx});
                }
            }
            return feed;
        }

        public void follow(int followerId, int followeeId) {
            if (followerId != followeeId) {
                following.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
            }
        }

        public void unfollow(int followerId, int followeeId) {
            Set<Integer> set = following.get(followerId);
            if (set != null) {
                set.remove(followeeId);
            }
        }
    }
}
