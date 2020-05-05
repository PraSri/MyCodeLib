package LinkedList;

public class MergeTwoSortedLists {

	public static void main(String[] args) {

	}

	public ListNode mergeTwoLists(ListNode A, ListNode B) {

		if (A == null)
			return B;
		if (B == null)
			return A;
		ListNode c;
		if (A.val < B.val) {
			c = A;
			c.next = mergeTwoLists(A.next, B);
		} else {
			c = B;
			c.next = mergeTwoLists(A, B.next);
		}
		return c;

	}

	public ListNode mergeTwoLists_V2(ListNode A, ListNode B) {

		if (A == null)
			return B;
		if (B == null)
			return A;
		if (A.val < B.val) {
			A.next = mergeTwoLists(A.next, B);
			return A;
		} else {
			B.next = mergeTwoLists(A, B.next);
			return B;
		}

	}
}
