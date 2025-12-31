package linkedlist;

/**
 * Linked List Cycle II
 * https://leetcode.com/problems/linked-list-cycle-ii/
 * <p>
 * Happy Number
 * https://leetcode.com/problems/happy-number/
 */
public class LinkedListCycleDetection {

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
    /**
     * <a href="https://leetcode.com/problems/linked-list-cycle-ii/">LeetCode - Linked List Cycle II</a>
     */
    public static class LinkedListCycleIi {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/happy-number/">LeetCode - Happy Number</a>
     */
    public static class HappyNumber {
        // placeholder
    }

}
