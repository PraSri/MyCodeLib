package LinkedList;

public class Clonealinkedlist {

	public static void main(String[] args) {

	}

	ListNode cloneList(ListNode A) {

		// insert new nodes in between

		ListNode curr = A;
		while (curr != null) {
			ListNode nxt = curr.next;
			ListNode temp = new ListNode(curr.val);
			curr.next = temp;
			temp.next = nxt;
			curr = nxt;
		}

		// update random pointers of new nodes

		curr = A;

		while (curr != null) {
			curr.next.random = curr.random.next;
			curr = curr.next.next;
		}
		ListNode curr1 = A, curr2 = A.next, h1 = A, h2 = A.next;

		while (curr1 != null && curr2 != null) {
			curr1.next = curr2.next;
			if (curr2.next == null) {
				curr2.next = null;
			} else {
				curr2.next = curr2.next.next;
			}
			curr1 = curr1.next;
			curr2 = curr2.next;
		}
		return h2;

	}

	class ListNode {
		int val;
		ListNode next, random;

		ListNode(int x) {
			val = x;
			next = random = null;
		}
	}

}
