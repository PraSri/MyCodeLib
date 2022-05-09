package LinkedList;

public class RemoveDuplicatesFromSortedList {

	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
	}

	/*
	 * Given a sorted linked list, delete all duplicates such that each element
	 * appear only once.
	 */
	public ListNode deleteDuplicates(ListNode A) {

		ListNode head = A, next = null;

		while (head != null) {
			next = head.next;
			if (next == null) {
				break;
			}
			if (head.val == next.val) {
				head.next = next.next;
			} else {
				head = head.next;
			}

		}

		return A;

	}

	/*
	 * 
	 * Given a sorted linked list, delete all nodes that have duplicate numbers,
	 * leaving only distinct numbers from the original list.
	 * 
	 * For example, Given 1->2->3->3->4->4->5, return 1->2->5. Given 1->1->1->2->3,
	 * return 2->3.
	 * 
	 * 
	 */

	public ListNode deleteDuplicates2(ListNode A) {
		
		if(A==null) {
			return null;
		}

		ListNode fake = new ListNode(0);
		fake.next = A;
		ListNode curr = A;
		ListNode prev = fake;

		while (curr != null) {

			while (curr.next != null && curr.val == curr.next.val) {

				curr = curr.next;

			}

			if (prev.next == curr) {
				prev = prev.next;
			} else {
				prev.next = curr.next;
			}

			curr = curr.next;

		}

		return fake.next;

	}
	
	public ListNode deleteDuplicates(ListNode head) {
        ListNode fake = new ListNode(0, head);
        ListNode prev = fake;
        while(head!=null) {
            if(head.next!=null && head.val == head.next.val) {
                while(head.next!=null && head.val==head.next.val) {
                    head = head.next;
                }
                prev.next = head.next;
            }else {
                prev = prev.next;
            }
            
            head = head.next;
            
        }
        return fake.next;
    }

}
