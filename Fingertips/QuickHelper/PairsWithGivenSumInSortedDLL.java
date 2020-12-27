package QuickHelper;

import LinkedList.ListNode;

public class PairsWithGivenSumInSortedDLL {

	public static void main(String[] args) {

	}

	// https://www.geeksforgeeks.org/find-pairs-given-sum-doubly-linked-list/
	public static int countPairWithGivenSum(ListNode head, int x) {

		int c = 0;

		ListNode left = head, right = head;
		while (right.next != null) {
			right = right.next;
		}

		return getCount(x, c, left, right);

	}

	public static int getCount(int x, int c, ListNode left, ListNode right) {
		while (left != null && right != null && left != right && left != right.next) {

			if (left.val + right.val == x) {
				c++;
				left = left.next;
				right = right.prev;
			} else if (left.val + right.val > x) {
				right = right.prev;
			} else {
				left = left.next;
			}

		}

		return c;
	}

}
