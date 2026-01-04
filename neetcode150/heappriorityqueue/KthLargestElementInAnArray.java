package heappriorityqueue;

import java.util.*;

/**Wiggle Sort II
https://leetcode.com/problems/wiggle-sort-ii/

Top K Frequent Elements
https://leetcode.com/problems/top-k-frequent-elements/

Third Maximum Number
https://leetcode.com/problems/third-maximum-number/

Kth Largest Element in a Stream
https://leetcode.com/problems/kth-largest-element-in-a-stream/

K Closest Points to Origin
https://leetcode.com/problems/k-closest-points-to-origin/

Find the Kth Largest Integer in the Array
https://leetcode.com/problems/find-the-kth-largest-integer-in-the-array/

Find Subsequence of Length K With the Largest Sum
https://leetcode.com/problems/find-subsequence-of-length-k-with-the-largest-sum/

K Highest Ranked Items Within a Price Range
https://leetcode.com/problems/k-highest-ranked-items-within-a-price-range/*/

public class KthLargestElementInAnArray {

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int x: nums) {
            pq.add(x);
            if(pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }


    /**
     * Wiggle Sort II
     * https://leetcode.com/problems/wiggle-sort-ii
     */
    public static class WiggleSortIi {
    }

    /**
     * Top K Frequent Elements
     * https://leetcode.com/problems/top-k-frequent-elements
     */
    public static class TopKFrequentElements {
    }

    /**
     * Third Maximum Number
     * https://leetcode.com/problems/third-maximum-number
     */
    public static class ThirdMaximumNumber {
    }

    /**
     * Kth Largest Element in a Stream
     * https://leetcode.com/problems/kth-largest-element-in-a-stream
     */
    public static class KthLargestElementInAStream {
    }

    /**
     * K Closest Points to Origin
     * https://leetcode.com/problems/k-closest-points-to-origin
     */
    public static class KClosestPointsToOrigin {
    }

    /**
     * Find the Kth Largest Integer in the Array
     * https://leetcode.com/problems/find-the-kth-largest-integer-in-the-array
     */
    public static class FindTheKthLargestIntegerInTheArray {
    }

    /**
     * Find Subsequence of Length K With the Largest Sum
     * https://leetcode.com/problems/find-subsequence-of-length-k-with-the-largest-sum
     */
    public static class FindSubsequenceOfLengthKWithTheLargestSum {
    }

    /**
     * K Highest Ranked Items Within a Price Range
     * https://leetcode.com/problems/k-highest-ranked-items-within-a-price-range
     */
    public static class KHighestRankedItemsWithinAPriceRange {
    }
}
