package binarysearch;

import java.util.*;

// Stock Price Fluctuation - https://leetcode.com/problems/stock-price-fluctuation/

public class TimeBasedKeyValueStore {

    // key -> list(pair(timestamp, value))
    private final Map<String, List<Pair<Integer, String>>> keyStore;

    public TimeBasedKeyValueStore() {
        keyStore = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        keyStore.computeIfAbsent(key, k -> new ArrayList<>()).add(new Pair<>(timestamp, value));
    }

    //To answer a get(key, timestamp) query, we only need to find the latest timestamp that is ? the given timestamp.
    // Because timestamps are sorted, we can use binary search to quickly find this position instead of scanning everything.
    public String get(String key, int timestamp) {

        List<Pair<Integer, String>> values = keyStore.getOrDefault(key, new ArrayList<>());

        int left = 0, right = values.size() - 1;

        String result = "";

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (values.get(mid).getKey() <= timestamp) {

                result = values.get(mid).getValue();

                left = mid + 1;

            } else {

                right = mid - 1;

            }
        }

        return result;
    }

    private static class Pair<K, V> {
        private final K key;
        private final V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    /**
     * <a href="https://leetcode.com/problems/stock-price-fluctuation/">LeetCode - Stock Price Fluctuation</a>
     */
    public static class StockPriceFluctuation {
        // placeholder
    }
}
