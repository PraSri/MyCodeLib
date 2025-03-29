package two_pointers.top_K_elements;
// https://leetcode.com/problems/top-k-frequent-elements/

import java.util.*;

public class TopKFrequentElements2 {

    public int[] topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> frequency = new HashMap<>();

        for(int x : nums) {
            frequency.put(x, frequency.getOrDefault(x, 0) + 1);
        }

        Queue<Integer> minHeap = new PriorityQueue<>(
                Comparator.comparingInt(frequency::get)
        );

        for(int x : frequency.keySet()) {
            minHeap.add(x);
            if(minHeap.size()>k) {
                minHeap.poll();
            }
        }

        int[] top = new int[k];

        for(int i = k-1; i>=0; i--) {
            top[i] = minHeap.poll();
        }

        return top;
    }
}
