package two_heaps.find_median_from_data_stream;

import java.util.Collections;
import java.util.PriorityQueue;

public class Solution {

    public static class MedianFinder {
        private final PriorityQueue<Integer> low = new PriorityQueue<>(Collections.reverseOrder());
        private final PriorityQueue<Integer> high = new PriorityQueue<>();

        public MedianFinder() {
        }

        public void addNum(int num) {
            low.offer(num);
            high.offer(low.poll());
            if (high.size() > low.size()) {
                low.offer(high.poll());
            }
        }

        public double findMedian() {
            if (low.size() > high.size()) {
                return low.peek();
            }
            return ((double) low.peek() + high.peek()) / 2.0;
        }
    }
}
