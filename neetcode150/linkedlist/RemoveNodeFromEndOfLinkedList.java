package linkedlist;

/**
 * Swapping Nodes in a Linked List
 * https://leetcode.com/problems/swapping-nodes-in-a-linked-list/
 * <p>
 * Delete N Nodes After M Nodes of a Linked List
 * https://leetcode.com/problems/delete-n-nodes-after-m-nodes-of-a-linked-list/
 * <p>
 * Delete the Middle Node of a Linked List
 * https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/
 */
public class RemoveNodeFromEndOfLinkedList {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode left = dummy;
        ListNode right = head;
        while (n > 0) {
            right = right.next;
            n--;
        }
        while (right != null) {
            left = left.next;
            right = right.next;
        }
        left.next = left.next.next;
        return dummy.next;
    }

    /**
     * <a href="https://leetcode.com/problems/swapping-nodes-in-a-linked-list/">LeetCode - Swapping Nodes in a Linked List</a>
     */
    public static class SwappingNodesInALinkedList {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/delete-n-nodes-after-m-nodes-of-a-linked-list/">LeetCode - Delete N Nodes After M Nodes of a Linked List</a>
     */
    public static class DeleteNNodesAfterMNodesOfALinkedList {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/">LeetCode - Delete the Middle Node of a Linked List</a>
     */
    public static class DeleteTheMiddleNodeOfALinkedList {
        // placeholder
    }

}
