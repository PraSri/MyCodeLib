package in_place_reversal.reverse_nodes_in_k_group;

import common.ListNode;

public class Solution {

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        ListNode groupPrev = dummy;
        while (true) {
            ListNode kth = groupPrev;
            for (int i = 0; i < k && kth != null; i++) {
                kth = kth.next;
            }
            if (kth == null) {
                break;
            }
            ListNode groupNext = kth.next;
            ListNode prev = groupNext;
            ListNode cur = groupPrev.next;
            while (cur != groupNext) {
                ListNode next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            }
            ListNode oldFirst = groupPrev.next;
            groupPrev.next = kth;
            groupPrev = oldFirst;
        }
        return dummy.next;
    }
}
