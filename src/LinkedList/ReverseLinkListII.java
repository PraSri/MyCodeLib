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

	// looks more intrutive to me
	public ListNode reverseBetween_v2(ListNode head, int m, int n) {
		ListNode fakeHead = new ListNode(-1);
		fakeHead.next = head;
		ListNode prev = fakeHead;
		ListNode curr = fakeHead.next;
		int i = 1;
		while (i < m) {
			prev = curr;
			curr = curr.next;
			i++;
		}
		ListNode node = prev;
		while (i <= n) {
			ListNode tmp = curr.next;
			curr.next = prev;
			prev = curr;
			curr = tmp;
			i++;
		}
		node.next.next = curr;
		node.next = prev;
		return fakeHead.next;
	}
	
// 	for 1->2->3->4->5->null, m = 2, n = 4,
// after the second while, we got 1<->2<-3<-4 5->null
// now cur = 5, pre = 4, node = 1,
// so node.next = 2, node.next.next = cur means 2->5;
// node.next = pre means 1->4, then we get the result 1->4->3->2->5->null.
	
	
}
