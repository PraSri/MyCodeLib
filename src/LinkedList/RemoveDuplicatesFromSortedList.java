package LinkedList;

public class RemoveDuplicatesFromSortedList {

	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
	}

	public ListNode deleteDuplicates(ListNode A) {

		ListNode head = A, next = null;

		while (head != null) {
			next = head.next;
			if (head.val == next.val) {
				head.next = next.next;
			} else {
				head = head.next;
			}

		}

		return A;

	}

}
