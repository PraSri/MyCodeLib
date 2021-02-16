package Amazon;

import java.util.HashMap;
import java.util.Map;

import LinkedList.ListNode;
import QuickHelper.PairsWithGivenSumInSortedDLL;

public class LinkedList_CountTripletsInSortedDoublyLL {

	public static void main(String[] args) {
	}

	/**
	 * Count triplets in a sorted doubly linked list whose sum is equal to a given
	 * value x
	 ***/

	/****
	 * Given a sorted doubly linked list of distinct nodes(no two nodes have the
	 * same data) and a value x. Count triplets in the list that sum up to a given
	 * value x.
	 ***********/

	// method 1 : naive O(n^3) space : O(1)

	public static int solve_v1(ListNode head, int x) {

		int c = 0;
		ListNode p1, p2, p3;
		for (p1 = head; p1 != null; p1 = p1.next) {
			for (p2 = p1.next; p2 != null; p2 = p2.next) {
				for (p3 = p2.next; p3 != null; p3 = p3.next) {
					if (p1.val + p2.val + p3.val == x) {
						c++;
					}
				}
			}
		}

		return c;

	}

	// method 2 : hashing O(n^2) space : O(n)

	public static int solve_v2(ListNode head, int x) {

		int c = 0;

		Map<Integer, ListNode> m = new HashMap<Integer, ListNode>();

		for (ListNode p = head; p != null; p = p.next) {
			m.put(p.val, p);
		}

		for (ListNode i = head; i != null; i = i.next) {
			for (ListNode j = i.next; j != null; j = j.next) {
				int sum = i.val + j.val;
				if (m.containsKey(x - sum) && m.get(x - sum) != i && m.get(x - sum) != j) {
					c++;
				}
			}
		}

		return c / 3;

	}

	// method 3 : two pointer O(n^2) space : O(1)

	public static int solve_v3(ListNode head, int x) {

		int c = 0;
		ListNode curr, first, last;
		last = head;
		while (last.next != null) {
			last = last.next;
		}

		for (curr = head; curr != null; curr = curr.next) {
			first = curr.next;
			c += countPairs(first, last, x - curr.val);
		}

		return c;

	}

	private static int countPairs(ListNode first, ListNode last, int x) {

		return PairsWithGivenSumInSortedDLL.getCount(x, 0, first, last);
	}

}
