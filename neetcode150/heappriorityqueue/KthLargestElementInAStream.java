package heappriorityqueue;

import java.util.*;

/**Kth Largest Element in an Array (Medium)
https://leetcode.com/problems/kth-largest-element-in-an-array/

Finding MK Average (Hard)
https://leetcode.com/problems/finding-mk-average/

Sequentially Ordinal Rank Tracker (Hard)
https://leetcode.com/problems/sequentially-ordinal-rank-tracker/*/

public class KthLargestElementInAStream {

    private final int k;
    private final PriorityQueue<Integer> minHeap;
    public KthLargestElementInAStream(int k, int[] nums) {
        this.k = k;
        this.minHeap = new PriorityQueue<>();
        for(int x: nums) {
            add(x);
        }
    }

    public int add(int val) {
        minHeap.offer(val);
        if(minHeap.size() > k) {
            minHeap.poll();
        }
        return minHeap.peek();
    }


    /**
     * Kth Largest Element in an Array
     * https://leetcode.com/problems/kth-largest-element-in-an-array
     */
    public static class KthLargestElementInAnArray {
    }

    /**
     * Finding MK Average
     * https://leetcode.com/problems/finding-mk-average
     */
    public static class FindingMkAverage {
    }

    /**
     * Sequentially Ordinal Rank Tracker
     * https://leetcode.com/problems/sequentially-ordinal-rank-tracker
     */
    public static class SequentiallyOrdinalRankTracker {
    }
}
