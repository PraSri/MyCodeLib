import java.util.*;

public class HeapPriorityQueue {

    // Kth Largest Element in a Stream
    // took 7 mins in first attempt, with first run wrong answer
    // even easy question can be difficult for you, if full focus is there

    PriorityQueue<Integer> minHeap;
    int k;

    public void KthLargest(int k, int[] nums) {
        this.k = k;
        minHeap = new PriorityQueue<>();
        for (int x : nums) {
            add(x);
        }
    }

    public int add(int val) {
        minHeap.add(val);
        if (minHeap.size() > k) {
            minHeap.poll();
        }
        return minHeap.peek();
    }

    // Last Stone Weight - 3mins
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int x : stones) {
            maxHeap.add(x);
        }
        while (!maxHeap.isEmpty()) {
            int x = maxHeap.poll();
            if (maxHeap.isEmpty()) {
                return x;
            } else {
                int y = maxHeap.poll();
                if (x != y) {
                    maxHeap.add(Math.abs(y - x));
                }
            }
        }
        return maxHeap.isEmpty() ? 0 : maxHeap.poll();
    }

    // KCloset Points To Origin - took around 7 mins - practice hard
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<Point> minHeap = new PriorityQueue<>(Comparator.comparingInt(x -> x.d));
        for (int[] point : points) {
            minHeap.offer(new Point(point[0], point[1]));
        }
        int[][] res = new int[k][2];
        int i = 0;
        while (!minHeap.isEmpty() && k > 0) {
            Point point = minHeap.poll();
            res[i][0] = point.x;
            res[i][1] = point.y;
            i++;
            k--;
        }
        return res;

    }

    // Kth Largest Element in an Array - within 1 min
    public int findKthLargest(int[] nums, int k) {
        minHeap.clear();

        for (int x : nums) {
            minHeap.offer(x);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }

    // Task Scheduler
    // Input: tasks = ["X","X","Y","Y"], n = 2
    // x Y _ xY
    //Output: 5
    // with 10 mins, not able to think about approach, practice hard.
    public int leastInterval(char[] tasks, int n) {

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int[] count = new int[26];
        for (char task : tasks) {
            count[task - 'A']++;
        }
        for (int cnt : count) {
            if (cnt > 0) {
                maxHeap.add(cnt);
            }
        }
        int time = 0;
        Queue<int[]> queue = new LinkedList<>();
        while (!maxHeap.isEmpty() || !queue.isEmpty()) {
            time++;
            if (maxHeap.isEmpty()) {
                time = queue.peek()[1];
            } else {
                int cnt = maxHeap.poll() - 1;
                if (cnt > 0) {
                    queue.add(new int[]{cnt, time + n});
                }
            }
            if (!queue.isEmpty() && queue.peek()[1] == time) {
                maxHeap.add(queue.poll()[0]);
            }
        }

        return time;

    }

    // K Closet points to Origin
    static class Point {
        int x, y, d;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.d = x * x + y * y;
        }
    }

    // Design Twitter

    static class Twitter {

        static int timestamp = 0;
        Map<Integer, User> userMap;

        public Twitter() {
            this.userMap = new HashMap<>();
        }

        public void postTweet(int userId, int tweetId) {
            if (!userMap.containsKey(userId)) {
                userMap.put(userId, new User(userId));
            }
            userMap.get(userId).post(tweetId);
        }

        public List<Integer> getNewsFeed(int userId) {
            List<Integer> res = new ArrayList<>();
            if (!userMap.containsKey(userId)) {
                return res;
            }
            Set<Integer> users = userMap.get(userId).followed;
            PriorityQueue<Tweet> queue = new PriorityQueue<>(users.size(), (a, b) -> (b.time - a.time));
            for (int user : users) {
                Tweet tweet = userMap.get(user).tweetHead;
                if (tweet != null) {
                    queue.add(tweet);
                }
            }
            int n = 0;
            while (!queue.isEmpty() && n < 10) {
                Tweet tweet = queue.poll();
                res.add(tweet.id);
                n++;
                if (tweet.next != null) {
                    queue.add(tweet.next);
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
            if (!userMap.containsKey(followerId) || !userMap.containsKey(followeeId)) {
                return;
            }
            userMap.get(followerId).unfollow(followeeId);
        }

        static class User {
            int id;
            Set<Integer> followed;
            Tweet tweetHead;

            public User(int id) {
                this.id = id;
                followed = new HashSet<>();
                follow(id);
                this.tweetHead = null;
            }

            void follow(int id) {
                followed.add(id);
            }

            void unfollow(int id) {
                followed.remove(id);
            }

            void post(int id) {
                Tweet tweet = new Tweet(id);
                tweet.next = tweetHead;
                tweetHead = tweet;
            }
        }

        static class Tweet {
            int id;
            int time;
            Tweet next;

            public Tweet(int id) {
                this.id = id;
                this.time = timestamp++;
                this.next = null;
            }
        }
    }

    // Find median from data stream
    static class MedianFinder {

        PriorityQueue<Integer> minHeap;
        PriorityQueue<Integer> maxHeap;

        public MedianFinder() {
            minHeap = new PriorityQueue<>();
            maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        }

        public void addNum(int num) {
            minHeap.add(num);
            maxHeap.add(minHeap.poll());
            if (minHeap.size() < maxHeap.size()) {
                minHeap.offer(maxHeap.poll());
            }
        }

        public double findMedian() {

            if (minHeap.size() > maxHeap.size()) {
                return minHeap.peek();
            }

            return (minHeap.peek() + maxHeap.peek()) / 2.0;

        }
    }

}
