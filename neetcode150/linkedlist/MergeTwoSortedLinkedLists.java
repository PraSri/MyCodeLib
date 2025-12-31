package linkedlist;

/**
 * Merge k Sorted Lists
 * https://leetcode.com/problems/merge-k-sorted-lists/
 * <p>
 * Merge Sorted Array
 * https://leetcode.com/problems/merge-sorted-array/
 * <p>
 * Sort List
 * https://leetcode.com/problems/sort-list/
 * <p>
 * Shortest Word Distance II
 * https://leetcode.com/problems/shortest-word-distance-ii/
 * <p>
 * Add Two Polynomials Represented as Linked Lists
 * https://leetcode.com/problems/add-two-polynomials-represented-as-linked-lists/
 * <p>
 * Longest Common Subsequence Between Sorted Arrays
 * https://leetcode.com/problems/longest-common-subsequence-between-sorted-arrays/
 * <p>
 * Merge Two 2D Arrays by Summing Values
 * https://leetcode.com/problems/merge-two-2d-arrays-by-summing-values/
 */
public class MergeTwoSortedLinkedLists {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        return merge(list1, list2);
    }

    private ListNode merge(ListNode a, ListNode b) {
        ListNode c;
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        if (a.val < b.val) {
            c = a;
            c.next = merge(a.next, b);
        } else {
            c = b;
            c.next = merge(a, b.next);
        }
        return c;
    }
    /**
     * <a href="https://leetcode.com/problems/merge-k-sorted-lists/">LeetCode - Merge k Sorted Lists</a>
     */
    public static class MergeKSortedLists {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/merge-sorted-array/">LeetCode - Merge Sorted Array</a>
     */
    public static class MergeSortedArray {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/sort-list/">LeetCode - Sort List</a>
     */
    public static class SortList {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/shortest-word-distance-ii/">LeetCode - Shortest Word Distance II</a>
     */
    public static class ShortestWordDistanceIi {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/add-two-polynomials-represented-as-linked-lists/">LeetCode - Add Two Polynomials Represented as Linked Lists</a>
     */
    public static class AddTwoPolynomialsRepresentedAsLinkedLists {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/longest-common-subsequence-between-sorted-arrays/">LeetCode - Longest Common Subsequence Between Sorted Arrays</a>
     */
    public static class LongestCommonSubsequenceBetweenSortedArrays {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/merge-two-2d-arrays-by-summing-values/">LeetCode - Merge Two 2D Arrays by Summing Values</a>
     */
    public static class MergeTwo2DArraysBySummingValues {
        // placeholder
    }

}
