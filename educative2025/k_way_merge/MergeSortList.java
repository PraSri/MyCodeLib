package k_way_merge;

import linked_list.ReverseKGroups;
import linked_list.ReverseKGroups.LinkedListNode;

import java.util.List;

public class MergeSortList {

	// Helper function
	public static ReverseKGroups.LinkedListNode merge2Lists(LinkedListNode head1, LinkedListNode head2) {
		LinkedListNode dummy = new LinkedListNode(-1);
		LinkedListNode prev = dummy; // set prev pointer to dummy node
		// traverse over the lists until both or one of them becomes null
		while (head1 != null && head2 != null) {
			// if l1 value is<=  l2 value, add l1 node to the list
			if (head1.data<= head2.data) {
				prev.next = head1;
				head1 = head1.next;
			} else {
				// if l1 value is greater than l2 value, add l2 node to the list
				prev.next = head2;
				head2 = head2.next;
			}
			prev = prev.next;
		}

		if (head1 == null)
			prev.next = head2;
		else
			prev.next = head1;

		return dummy.next;
	}

	// Main function
	public static LinkedListNode mergeKLists(List<ReverseKGroups.LinkedList1> lists) {
		if (lists.size() > 0) {
            int step = 1;
            while(step < lists.size())
            {
                // The loop merges lists that are 'step' apart.
				// For example, if we have 4 lists (L1, L2, L3, L4), with step = 1,
				//we'll first merge L1 with L2, and L3 with L4 etc,
                // After each iteration, the step size doubles (step *= 2).
				// This merges the results of previous merges,
                // For example, after the first iteration with step = 1,
				// we merge the results of L1 + L2 with L3 + L4 in the next iteration when step = 2.
                for(int i = 0; i < lists.size() - step;  i += step * 2)
                {
                    lists.get(i).head =  merge2Lists(lists.get(i).head, lists.get(i + step).head);
                }
                step *= 2;
            }
			return lists.get(0).head;
        }
		else
		    return null;
		
	}
	
	@SuppressWarnings("unchecked")
	// Driver code
	public static void main(String[] args) {

		System.out.println("hello");
	}
}
