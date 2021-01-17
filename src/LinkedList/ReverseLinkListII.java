package LinkedList;

public class ReverseLinkListII {

	public static void main(String[] args) {

	}

	// Reverse a linked list from position m to n. Do it in one-pass.

	public ListNode reverseBetween(ListNode A, int B, int C) {

		ListNode dummy = new ListNode(-1);
		dummy.next = A;

		ListNode rpre, pre, curr;

		pre = dummy;

		for (int i = 0; i < B - 1; i++) {
			pre = pre.next;
		}

		rpre = pre;

		pre = pre.next; // position of B ..i.e. start

		curr = pre.next;

		for (int i = B; i < C; i++) {
			pre.next = curr.next;
			curr.next = rpre.next;
			rpre.next = curr;
			curr = pre.next;
		}

		return dummy.next;

	}

}
