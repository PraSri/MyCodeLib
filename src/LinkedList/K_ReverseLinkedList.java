package LinkedList;

public class K_ReverseLinkedList {

	public static void main(String[] args) {

		/*
		 * A = [1, 2, 3, 4, 5, 6] B = 2
		 * 
		 * 2,1,4,3,6,5
		 * 
		 * 1-2-null reverseLL(1) = 2-1-null l1 3-4-null reverseLL(3) = 4-3-null l2
		 * 5-6-null reverseLL(5) = 6-5-null l3 join() = l1-l2-l3-null
		 * 
		 * not efficient
		 * 
		 */

	}

	public static ListNode reverseList(ListNode A, int B) {
		ListNode head = A;
		int k = B;
		ListNode dummyHead = new ListNode(-1);
		dummyHead.next = head;
		ListNode begin = dummyHead;
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

		return dummyHead.next;
	}

	private static ListNode reverse(ListNode begin, ListNode end) {
		ListNode curr = begin.next, prev = begin, next, first = begin.next;
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

}
