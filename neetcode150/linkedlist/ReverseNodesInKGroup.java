package linkedlist;


/**
 * Swap Nodes in Pairs
 * https://leetcode.com/problems/swap-nodes-in-pairs/
 * <p>
 * Swapping Nodes in a Linked List
 * https://leetcode.com/problems/swapping-nodes-in-a-linked-list/
 * <p>
 * Reverse Nodes in Even Length Groups
 * https://leetcode.com/problems/reverse-nodes-in-even-length-groups/
 **/

public class ReverseNodesInKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode begin = dummy;

        if (head == null || head.next == null || k == 1) {
            return head;
        }

        int i = 0;
        while (head != null) {
            i++;
            if (i % k == 0) {
                begin = reverse(begin, head.next);
                head = begin.next;
            } else {
                head = head.next;
            }
        }

        return dummy.next;

    }

    private ListNode reverse(ListNode begin, ListNode end) {
        ListNode curr = begin.next;
        ListNode prev = begin;
        ListNode next = null;
        ListNode first = begin.next;
        while (curr != end) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        begin.next = prev;
        first.next = curr;
        return first;
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
     * <a href="https://leetcode.com/problems/swap-nodes-in-pairs/">LeetCode - Swap Nodes in Pairs</a>
     */
    public static class SwapNodesInPairs {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/swapping-nodes-in-a-linked-list/">LeetCode - Swapping Nodes in a Linked List</a>
     */
    public static class SwappingNodesInALinkedList {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/reverse-nodes-in-even-length-groups/">LeetCode - Reverse Nodes in Even Length Groups</a>
     */
    public static class ReverseNodesInEvenLengthGroups {
        // placeholder
    }

}
