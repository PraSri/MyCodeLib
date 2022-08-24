package a_linkedlist;

import LinkedList.ListNode;

public class SortList {

    public ListNode sortList(ListNode head) {

        if(head==null || head.next==null)
            return head;

        ListNode mid = getMid(head);
        ListNode left = sortList(head);
        ListNode right = sortList(mid);
        return merge(left, right);

    }

    public ListNode getMid(ListNode head) {
        ListNode slow = head, fast = head.next;

        while(fast!=null && fast.next!=null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode mid = slow.next;
        slow.next = null;
        return mid;

    }

    public ListNode merge(ListNode a, ListNode b) {
        ListNode fake = new ListNode();
        ListNode curr = fake;
        while(a!=null && b!=null) {
            if(a.val < b.val) {
                curr.next = a;
                a = a.next;
                curr = curr.next;
            } else {
                curr.next = b;
                b = b.next;
                curr = curr.next;
            }
        }
        curr.next = (a!=null) ? a : b;
        return fake.next;
    }
}
