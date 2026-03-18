package GoldmanSachs;

import LinkedList.ListNode;

public class CheckLL_is_palindrome {
//(Use a Stack) 

	// This can be solved by reversing the 2nd half and compare the two halves.

	public boolean isPalindrome(ListNode head) {

		ListNode fast = head, slow = head;

		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}

		if (fast != null) { // odd nodes: let right half smaller
			slow = slow.next;
		}

		slow = reverse(slow);
		fast = head;

		while (slow != null) {
			if (fast.val != slow.val) {
				return false;
			}
			fast = fast.next;
			slow = slow.next;
		}

		return true;
	}

	public ListNode reverse(ListNode head) {
		
		ListNode prev = null;
		
		while (head != null) {
			ListNode next = head.next;
			head.next = prev;
			prev = head;
			head = next;
		}
		return prev;
	}
}
