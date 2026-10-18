package top_k_heap.kth_largest_element_in_a_stream;

import java.util.PriorityQueue;

public class Solution {

    public static class KthLargest {
        private final int k;
        private final PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        public KthLargest(int k, int[] nums) {
            this.k = k;
            for (int num : nums) {
                add(num);
            }
        }

        public int add(int val) {
            if (minHeap.size() < k) {
                minHeap.offer(val);
            } else if (val > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(val);
            }
            return minHeap.peek();
        }
    }
}
