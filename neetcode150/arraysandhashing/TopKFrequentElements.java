package arraysandhashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Word Frequency
 * <a href="https://leetcode.com/problems/word-frequency/">LeetCode - Word Frequency</a>
 * <p>
 * Frequency counting + sorting
 * <p>
 * Kth Largest Element in an Array
 * <a href="https://leetcode.com/problems/kth-largest-element-in-an-array/">LeetCode - Kth Largest Element in an Array</a>
 * <p>
 * Top-K / Heap / Quickselect pattern
 * <p>
 * Sort Characters By Frequency
 * <a href="https://leetcode.com/problems/sort-characters-by-frequency/">LeetCode - Sort Characters By Frequency</a>
 * <p>
 * Character frequency + sorting
 * <p>
 * Split Array into Consecutive Subsequences
 * <a href="https://leetcode.com/problems/split-array-into-consecutive-subsequences/">LeetCode - Split Array into Consecutive Subsequences</a>
 * <p>
 * Greedy + frequency map
 * <p>
 * Top K Frequent Words
 * <a href="https://leetcode.com/problems/top-k-frequent-words/">LeetCode - Top K Frequent Words</a>
 * <p>
 * Same as this question but with words
 * <p>
 * K Closest Points to Origin
 * <a href="https://leetcode.com/problems/k-closest-points-to-origin/">LeetCode - K Closest Points to Origin</a>
 * <p>
 * Top-K selection using heap
 * <p>
 * Sort Features by Popularity
 * <a href="https://leetcode.com/problems/sort-features-by-popularity/">LeetCode - Sort Features by Popularity</a>
 * <p>
 * Frequency + custom sorting
 * <p>
 * Sender With Largest Word Count
 * <a href="https://leetcode.com/problems/sender-with-largest-word-count/">LeetCode - Sender With Largest Word Count</a>
 * <p>
 * Frequency counting
 * <p>
 * Most Frequent Even Element
 * <a href="https://leetcode.com/problems/most-frequent-even-element/">LeetCode - Most Frequent Even Element</a>
 * <p>
 * Simple frequency map
 * <p>
 * Linked List Frequency
 * <a href="https://leetcode.com/problems/linked-list-frequency/">LeetCode - Linked List Frequency</a>
 * <p>
 * Frequency counting on linked list
 */

class TopKFrequentElements {

    //Core Idea (Bucket Sort Feel)
    //
    //Pehle har number ki frequency count karo
    //
    //Phir frequency ke basis pe buckets banao
    //
    //High frequency se low ki taraf traverse karke top k nikaal lo

    //Time & Space Complexity
    //
    //Time: O(n)
    //
    //Space: O(n)
    //
    //Heap approach O(n log k) hota, but ye better & optimal hai

    public int[] topKFrequent(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> count = new HashMap<>();
        List<Integer>[] frq = new List[n + 1];
        for (int i = 0; i < n; i++) {
            count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
        }
        for (int i = 0; i <= n; i++) {
            frq[i] = new ArrayList<>();
        }
        for (Map.Entry<Integer, Integer> me : count.entrySet()) {
            frq[me.getValue()].add(me.getKey());
        }
        int[] ans = new int[k];
        int j = 0;
        for (int i = frq.length - 1; i > 0 && j < k; i--) {
            for (int f : frq[i]) {
                ans[j++] = f;
                if (j == k) {
                    break;
                }
            }
        }
        return ans;
    }

    // ====== Empty static inner classes for referenced LeetCode questions ======

    /**
     * Word Frequency
     * <a href="https://leetcode.com/problems/word-frequency/">LeetCode - Word Frequency</a>
     */
    public static class WordFrequency {
        // Placeholder for LeetCode - Word Frequency
    }

    /**
     * Kth Largest Element in an Array
     * <a href="https://leetcode.com/problems/kth-largest-element-in-an-array/">LeetCode - Kth Largest Element in an Array</a>
     */
    public static class KthLargestElementInAnArray {
        // Placeholder for LeetCode - Kth Largest Element in an Array
    }

    /**
     * Sort Characters By Frequency
     * <a href="https://leetcode.com/problems/sort-characters-by-frequency/">LeetCode - Sort Characters By Frequency</a>
     */
    public static class SortCharactersByFrequency {
        // Placeholder for LeetCode - Sort Characters By Frequency
    }

    /**
     * Split Array into Consecutive Subsequences
     * <a href="https://leetcode.com/problems/split-array-into-consecutive-subsequences/">LeetCode - Split Array into Consecutive Subsequences</a>
     */
    public static class SplitArrayIntoConsecutiveSubsequences {
        // Placeholder for LeetCode - Split Array into Consecutive Subsequences
    }

    /**
     * Top K Frequent Words
     * <a href="https://leetcode.com/problems/top-k-frequent-words/">LeetCode - Top K Frequent Words</a>
     */
    public static class TopKFrequentWords {
        // Placeholder for LeetCode - Top K Frequent Words
    }

    /**
     * K Closest Points to Origin
     * <a href="https://leetcode.com/problems/k-closest-points-to-origin/">LeetCode - K Closest Points to Origin</a>
     */
    public static class KClosestPointsToOrigin {
        // Placeholder for LeetCode - K Closest Points to Origin
    }

    /**
     * Sort Features by Popularity
     * <a href="https://leetcode.com/problems/sort-features-by-popularity/">LeetCode - Sort Features by Popularity</a>
     */
    public static class SortFeaturesByPopularity {
        // Placeholder for LeetCode - Sort Features by Popularity
    }

    /**
     * Sender With Largest Word Count
     * <a href="https://leetcode.com/problems/sender-with-largest-word-count/">LeetCode - Sender With Largest Word Count</a>
     */
    public static class SenderWithLargestWordCount {
        // Placeholder for LeetCode - Sender With Largest Word Count
    }

    /**
     * Most Frequent Even Element
     * <a href="https://leetcode.com/problems/most-frequent-even-element/">LeetCode - Most Frequent Even Element</a>
     */
    public static class MostFrequentEvenElement {
        // Placeholder for LeetCode - Most Frequent Even Element
    }

    /**
     * Linked List Frequency
     * <a href="https://leetcode.com/problems/linked-list-frequency/">LeetCode - Linked List Frequency</a>
     */
    public static class LinkedListFrequency {
        // Placeholder for Linked List Frequency (LeetCode style)
    }

}
