package bucket_sort.top_k_frequent_elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    @SuppressWarnings("unchecked")
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.merge(num, 1, Integer::sum);
        }
        List<Integer>[] buckets = new List[nums.length + 1];
        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            int f = e.getValue();
            if (buckets[f] == null) {
                buckets[f] = new ArrayList<>();
            }
            buckets[f].add(e.getKey());
        }
        int[] result = new int[k];
        int idx = 0;
        for (int f = nums.length; f > 0 && idx < k; f--) {
            if (buckets[f] == null) {
                continue;
            }
            for (int value : buckets[f]) {
                if (idx == k) {
                    break;
                }
                result[idx++] = value;
            }
        }
        return result;
    }
}
