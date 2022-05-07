package LinkedList;

public class IntersectionOfLinkedLists {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	/*
	 * 
	 * https://leetcode.com/problems/intersection-of-two-linked-lists/discuss/49785/
	 * Java-solution-without-knowing-the-difference-in-len!
	 * 
	 */
	public ListNode getIntersectionNode(ListNode a, ListNode b) {

		ListNode pa = a, pb = b;

		if (pa == null || pb == null) {
			return null;
		}

		while (pa != pb) {
			pa = pa == null ? b : pa.next;
			pb = pb == null ? a : pb.next;
		}

		return pa;

	}

	public ListNode getIntersectionNode_v2(ListNode headA, ListNode headB) {
		// boundary check
		if (headA == null || headB == null)
			return null;

		ListNode a = headA;
		ListNode b = headB;

		// if a & b have different len, then we will stop the loop after second
		// iteration
		while (a != b) {
			// for the end of first iteration, we just reset the pointer to the head of
			// another linkedlist
			a = a == null ? headB : a.next;
			b = b == null ? headA : b.next;
		}

		return a;
	}
	
	public ListNode getIntersectionNodeWithLinearSpace(ListNode headA, ListNode headB) {
        
        ListNode h1 = headA;
        ListNode h2 = headB;
        Set<ListNode> set = new HashSet<>();
        while(h1!=null) {
            set.add(h1);
            h1 = h1.next;
        }
        
        while(h2!=null) {
            if(set.contains(h2)) {
                return h2;
            }
            h2=h2.next;
        }
        
        return null;
    }

}
