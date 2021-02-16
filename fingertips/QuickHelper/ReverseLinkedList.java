package QuickHelper;

import LinkedList.ListNode;

public class ReverseLinkedList {

	public static ListNode reverse(ListNode head) {

		ListNode curr = head, prev = null, next = null;

		while (curr != null) {

			next = curr.next;

			curr.next = prev;

			prev = curr;

			curr = next;

		}

		head = prev;

		return head;

	}

}
