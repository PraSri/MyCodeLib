package LinkedList;

public class ListNode {

	public int val;
	public ListNode next, prev;

	public ListNode(int x) {
		val = x;
		next = null;
		prev = null;
	}

	public ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}
}
