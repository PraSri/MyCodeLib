package a_linkedlist;

import LinkedList.ListNode;

public class ReverseLL {

    public ListNode reverseList(ListNode head) {
        ListNode curr = head, next = null, prev = null;
        while(curr!=null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
        return head;
    }
}
