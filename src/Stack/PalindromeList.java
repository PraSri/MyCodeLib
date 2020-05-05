package Stack;

import java.util.Stack;

import LinkedList.ListNode;

public class PalindromeList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/*
	 * Given a singly linked list A, determine if its a palindrome. Return 1 or 0
	 * denoting if its a palindrome or not, respectively.
	 */

	public static int lPalin(ListNode A) {

		Stack<Integer> s = new Stack();

		if (A == null || A.next == null) {
			return 1;
		}

		ListNode t = A;

		int l = 0;
		while (t != null) {
			t = t.next;
			l++;
		}
		int i = 1;
		t = A;
		while (i <= l / 2) {
			s.push(t.val);
			t = t.next;
			i++;
		}

		if (l % 2 != 0) {

			i++;
			t = t.next;

		}

		while (i <= l) {
			int x = 0;
			if (!s.empty()) {
				x = s.pop();
			}
			if (x != t.val) {
				return 0;
			}
			t = t.next;
			i++;
		}

		return 1;

	}

}
