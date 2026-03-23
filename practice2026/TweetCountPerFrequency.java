import java.util.*;

public class TweetCountPerFrequency {

    Map<String, TreeMap<Integer, Integer>> map;

    public TweetCountPerFrequency() {
        map = new HashMap<>();
    }

    public void recordTweet(String tweetName, int time) {
        if (!map.containsKey(tweetName)) {
            map.put(tweetName, new TreeMap<>());
        }
        TreeMap<Integer, Integer> tweetMap = map.get(tweetName);
        tweetMap.put(time, tweetMap.getOrDefault(time, 0) + 1);

    }

    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        List<Integer> result = new LinkedList<>();
        if (!map.containsKey(tweetName)) {
            return result;
        }
        int interval = 0;
        int size = 0;
        if ("minute".equals(freq)) {
            interval = 60;
        } else if ("hour".equals(freq)) {
            interval = 3600;
        } else if ("day".equals(freq)) {
            interval = 8600;
        }
        size = ((endTime - startTime) / interval) + 1;

        int[] buckets = new int[size];

        TreeMap<Integer, Integer> tweetMap = map.get(tweetName);

        for (Map.Entry<Integer, Integer> me : tweetMap.subMap(startTime, endTime).entrySet()) {
            int index = (me.getKey() - startTime) / interval;
            buckets[index] += me.getValue();
        }

        for (int num : buckets) {
            result.add(num);
        }
        return result;
    }
}
