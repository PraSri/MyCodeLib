package heappriorityqueue;

import java.util.*;

/**Sliding Window Median (Hard)
https://leetcode.com/problems/sliding-window-median/

Finding MK Average (Hard)
https://leetcode.com/problems/finding-mk-average/

Sequentially Ordinal Rank Tracker (Hard)
https://leetcode.com/problems/sequentially-ordinal-rank-tracker/

Minimum Operations to Make Median of Array Equal to K (Medium)
https://leetcode.com/problems/minimum-operations-to-make-median-of-array-equal-to-k/

Minimum Operations to Make Subarray Elements Equal (Medium)
https://leetcode.com/problems/minimum-operations-to-make-subarray-elements-equal/

Minimum Operations to Make Elements Within K Subarrays Equal (Hard)
https://leetcode.com/problems/minimum-operations-to-make-elements-within-k-subarrays-equal/*/

public class FindMedianFromDataStream {

    PriorityQueue<Integer> minHeap = null;
    PriorityQueue<Integer> maxHeap = null;

    public FindMedianFromDataStream() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    }

    public void addNum(int num) {
        minHeap.add(num);
        maxHeap.add(minHeap.poll());
        if(minHeap.size() < maxHeap.size()) {
            minHeap.offer(maxHeap.poll());
        }
    }

    public double findMedian() {
        if(minHeap.size()>maxHeap.size()) {
            return minHeap.peek();
        }
        return (minHeap.peek() + maxHeap.peek())/2.0;
    }

    /**
     * Sliding Window Median
     * https://leetcode.com/problems/sliding-window-median
     */
    public static class SlidingWindowMedian {
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

    /**
     * Minimum Operations to Make Median of Array Equal to K
     * https://leetcode.com/problems/minimum-operations-to-make-median-of-array-equal-to-k
     */
    public static class MinimumOperationsToMakeMedianOfArrayEqualToK {
    }

    /**
     * Minimum Operations to Make Subarray Elements Equal
     * https://leetcode.com/problems/minimum-operations-to-make-subarray-elements-equal
     */
    public static class MinimumOperationsToMakeSubarrayElementsEqual {
    }

    /**
     * Minimum Operations to Make Elements Within K Subarrays Equal
     * https://leetcode.com/problems/minimum-operations-to-make-elements-within-k-subarrays-equal
     */
    public static class MinimumOperationsToMakeElementsWithinKSubarraysEqual {
    }
}
