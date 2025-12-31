package linkedlist;

/**
 * Reverse Linked List II (Medium)
 * https://leetcode.com/problems/reverse-linked-list-ii/
 * <p>
 * Binary Tree Upside Down (Medium)
 * https://leetcode.com/problems/binary-tree-upside-down/
 * <p>
 * Palindrome Linked List (Easy)
 * https://leetcode.com/problems/palindrome-linked-list/
 * <p>
 * Reverse Nodes in Even Length Groups (Medium)
 * https://leetcode.com/problems/reverse-nodes-in-even-length-groups/
 * <p>
 * Maximum Twin Sum of a Linked List (Medium)
 * https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/
 * <p>
 * Remove Nodes From Linked List (Medium)
 * https://leetcode.com/problems/remove-nodes-from-linked-list/
 * <p>
 * Insert Greatest Common Divisors in Linked List (Medium)
 * https://leetcode.com/problems/insert-greatest-common-divisors-in-linked-list/
 */
public class ReverseLinkedList {
    // Input: head = [0,1,2,3]
    // 0 -> 1 -> 2 -> 3 -> null
    // prev = null
    // curr = 0
    // next = null
    // next = 1
    // prev = 0
    // curr = 1
    public ListNode reverseList(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        ListNode next = null;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
        return head;
    }
    /**
     * <a href="https://leetcode.com/problems/reverse-linked-list-ii/">LeetCode - Reverse Linked List II</a>
     */
    public static class ReverseLinkedListIi {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/binary-tree-upside-down/">LeetCode - Binary Tree Upside Down</a>
     */
    public static class BinaryTreeUpsideDown {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/palindrome-linked-list/">LeetCode - Palindrome Linked List</a>
     */
    public static class PalindromeLinkedList {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/reverse-nodes-in-even-length-groups/">LeetCode - Reverse Nodes in Even Length Groups</a>
     */
    public static class ReverseNodesInEvenLengthGroups {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/">LeetCode - Maximum Twin Sum of a Linked List</a>
     */
    public static class MaximumTwinSumOfALinkedList {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/remove-nodes-from-linked-list/">LeetCode - Remove Nodes From Linked List</a>
     */
    public static class RemoveNodesFromLinkedList {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/insert-greatest-common-divisors-in-linked-list/">LeetCode - Insert Greatest Common Divisors in Linked List</a>
     */
    public static class InsertGreatestCommonDivisorsInLinkedList {
        // placeholder
    }

}
