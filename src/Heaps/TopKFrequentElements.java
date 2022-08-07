package Heaps;// https://leetcode.com/problems/top-k-frequent-elements/

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class TopKFrequentElements {

    public int[] topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> frequency = new HashMap<>();

        for(int x : nums) {
            frequency.put(x, frequency.getOrDefault(x, 0) + 1);
        }

        Queue<Integer> minHeap = new PriorityQueue<>(
                (x1, x2) -> frequency.get(x1) - frequency.get(x2)
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
