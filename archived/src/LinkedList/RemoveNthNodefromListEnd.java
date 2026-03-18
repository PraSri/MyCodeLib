package LinkedList;

public class RemoveNthNodefromListEnd {

	public static void main(String[] args) {

	}

	public static int lengthOfList(ListNode head) {
		int l = 0;
		ListNode t = head;
		while (t != null) {
			t = t.next;
			l++;
		}
		return l;
	}

	public static ListNode removeNthFromEnd(ListNode A, int B) {

		if (B > lengthOfList(A)) {
			ListNode t = A.next;
			A = t;
			return A;
		}

		if (B == lengthOfList(A)) {
			ListNode t = A;
			while (t.next.next != null) {
				t = t.next;
			}
			t.next = null;
			return A;
		}

		ListNode s = A;
		ListNode f = A;
		ListNode prev = A;

		/*
		 * 
		 * Move f for k times Move s ,f 1 steps simultaneously return s
		 * 
		 * delete 3 from last l = 10 f = 3 s = 1 after this f & s move simultaneously 1
		 * step when f = 10 s will be on 7 remove 7th which 3rd from last
		 * 
		 */

		int n = B;

		while (n > 0) {
			f = f.next;
			n--;
		}

		while (f != null) {
			prev = s;
			f = f.next;
			s = s.next;
		}

		ListNode t = s.next;
		prev.next = t;

		return A;

	}

}
