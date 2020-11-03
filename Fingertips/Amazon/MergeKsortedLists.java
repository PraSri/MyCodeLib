package Amazon;

public class MergeKsortedLists {

	public static void main(String[] args) {

	}

	public class ListNode {
		public int value;
		public ListNode next;
	}

	public static ListNode mergeKLists(ListNode[] lists) {

		int n = lists.length;

		if (n == 0) {
			return null;
		}

		ListNode head = merge(lists, 0, n - 1);

		return head;

	}

	private static ListNode merge(ListNode[] lists, int s, int e) {

		if (s == e)
			return lists[e];
		int mid = s + (e - s) / 2;
		ListNode l1 = merge(lists, s, mid);
		ListNode l2 = merge(lists, mid + 1, e);

		return mergeTwoLists(l1, l2);
	}

	private static ListNode mergeTwoLists(ListNode a, ListNode b) {

		if (a == null)
			return b;

		if (b == null)
			return a;

		if (a.value < b.value) {

			a.next = mergeTwoLists(a.next, b);

			return a;

		}

		b.next = mergeTwoLists(a, b.next);

		return b;

	}

}
