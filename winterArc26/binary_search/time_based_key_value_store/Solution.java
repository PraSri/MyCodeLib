package binary_search.time_based_key_value_store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public static class TimeMap {
        private final Map<String, List<Integer>> times = new HashMap<>();
        private final Map<String, List<String>> values = new HashMap<>();

        public TimeMap() {
        }

        public void set(String key, String value, int timestamp) {
            times.computeIfAbsent(key, k -> new ArrayList<>()).add(timestamp);
            values.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
        }

        public String get(String key, int timestamp) {
            List<Integer> ts = times.get(key);
            if (ts == null) {
                return "";
            }
            int lo = 0;
            int hi = ts.size() - 1;
            int found = -1;
            while (lo <= hi) {
                int mid = (lo + hi) >>> 1;
                if (ts.get(mid) <= timestamp) {
                    found = mid;
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
            return found == -1 ? "" : values.get(key).get(found);
        }
    }
}
