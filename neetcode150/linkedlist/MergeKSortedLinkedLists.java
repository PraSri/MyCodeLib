package linkedlist;


/**
 * Merge Two Sorted Lists (Easy)
 * https://leetcode.com/problems/merge-two-sorted-lists/
 * <p>
 * Ugly Number II (Medium)
 * https://leetcode.com/problems/ugly-number-ii/
 * <p>
 * Smallest Subarrays With Maximum Bitwise OR (Medium)
 * https://leetcode.com/problems/smallest-subarrays-with-maximum-bitwise-or/
 */

public class MergeKSortedLinkedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        return sort(lists, 0, lists.length - 1);
    }

    private ListNode sort(ListNode[] lists, int s, int e) {
        if (s == e) {
            return lists[e];
        }
        int mid = s + (e - s) / 2;
        ListNode left = sort(lists, s, mid);
        ListNode right = sort(lists, mid + 1, e);
        return mergeLists(left, right);
    }

    private ListNode mergeLists(ListNode a, ListNode b) {
        ListNode c;
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        if (a.val < b.val) {
            c = a;
            c.next = mergeLists(a.next, b);
        } else {
            c = b;
            c.next = mergeLists(a, b.next);
        }
        return c;
    }
    /**
     * <a href="https://leetcode.com/problems/merge-two-sorted-lists/">LeetCode - Merge Two Sorted Lists</a>
     */
    public static class MergeTwoSortedLists {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/ugly-number-ii/">LeetCode - Ugly Number II</a>
     */
    public static class UglyNumberIi {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/smallest-subarrays-with-maximum-bitwise-or/">LeetCode - Smallest Subarrays With Maximum Bitwise OR</a>
     */
    public static class SmallestSubarraysWithMaximumBitwiseOr {
        // placeholder
    }

}
