package LinkedList;

public class LongestPalindromicList {

	public static void main(String[] args) {

	}

	public int solve(ListNode A) {

		int res = 0;

		ListNode prev = null, curr = A;
		while (curr != null) {
			ListNode next = curr.next;
			curr.next = prev;

			// for odd palindrome
			res = Math.max(res, 2*countCommonNodes(prev, next)+1);

			// for even palindrome
			res = Math.max(res, 2*countCommonNodes(curr, next));

			prev = curr;
			curr = next;
		}

		return res;
	}

	private int countCommonNodes(ListNode a, ListNode b) {
		int ct = 0;
		while (a != null && b != null) {
			if (a.val == b.val) {
				ct++;
			} else
				break;
			a = a.next;
			b = b.next;
		}

		return ct;
	}

}
