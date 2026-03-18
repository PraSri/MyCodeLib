package LinkedList;

public class FlattenGivenDoublyLinkedList {

	public static void main(String[] args) {

	}

	/******
	 * Given a linked list where every node represents a linked list and contains
	 * two pointers of its type:
	 * 
	 * Pointer to next node in the main list (right pointer) Pointer to a linked
	 * list where this node is head (down pointer). All linked lists are sorted.
	 * 
	 * You are asked to flatten the linked list into a single list. Use down pointer
	 * to link nodes of the flattened list. The flattened linked list should also be
	 * sorted.
	 * 
	 **************/

	class ListNode {
		int val;
		ListNode right, down;

		ListNode(int x) {
			val = x;
			right = down = null;
		}
	}

	ListNode flatten(ListNode root) {
		root = flattenUtil(root);
		return root;
	}

	private ListNode flattenUtil(ListNode root) {

		if (root == null || root.right == null) {
			return root;
		}

		root.right = flattenUtil(root.right);

		root = merge(root, root.right);

		return root;
	}

	private ListNode merge(ListNode a, ListNode b) {

		if (a == null)
			return b;
		if (b == null)
			return a;
		ListNode res = null;
		if (a.val < b.val) {
			res = a;
			res.down = merge(a.down, b);
		} else {
			res = b;
			res.down = merge(a, b.down);
		}

		res.right = null;

		return res;
	}

}
