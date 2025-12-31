package linkedlist;

/**
 * Delete the Middle Node of a Linked List
 * https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/
 * <p>
 * Take K of Each Character From Left and Right
 * https://leetcode.com/problems/take-k-of-each-character-from-left-and-right/
 */

public class ReorderLinkedList {

    public void reorderList(ListNode head) {
        // find mid
        // divide into 2 halves
        // reverse the 2nd half
        // merge both halves
        if (head == null) {
            return;
        }
        ListNode mid = mid(head);
        ListNode p2 = mid.next;
        mid.next = null;
        p2 = reverse(p2);
        ListNode h1 = head;
        ListNode h2 = p2;
        while (h2 != null) {
            ListNode next = h1.next;
            h1.next = h2;
            h1 = h2;
            h2 = next;
        }

    }

    private ListNode mid(ListNode head) {
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode reverse(ListNode head) {
        ListNode curr = head, next = null, prev = null;
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
     * <a href="https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/">LeetCode - Delete the Middle Node of a Linked List</a>
     */
    public static class DeleteTheMiddleNodeOfALinkedList {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/take-k-of-each-character-from-left-and-right/">LeetCode - Take K of Each Character From Left and Right</a>
     */
    public static class TakeKOfEachCharacterFromLeftAndRight {
        // placeholder
    }

}
