package linked_list;

import linked_list.ReverseKGroups.LinkedListNode;

public class ReverseNodesInEvenGroups {
	public static LinkedListNode reverseEvenLengthGroups(ReverseKGroups.LinkedListNode head)
	{
		// Node immediately before the current group
		LinkedListNode prev = head; 
		LinkedListNode node, reverse, currNext, curr, prevNext = null;

		// The head doesn't need to be reversed since it's a group of one node, 
		// so starts with length 2
		int groupLen = 2; 
		int numNodes = 0;
		while(prev.next!= null)
		{
			node = prev;
			numNodes = 0;

			// traversing all the nodes of the current group
			for (int i = 0; i < groupLen; i ++)
			{
				if(node.next == null)
                    break;
				numNodes += 1;
				node=node.next;
			}

			// odd length
			if(numNodes % 2 != 0) { 
			   prev = node; 
			} 

			// even length
			else {
				reverse = node.next;
				curr = prev.next;
                for(int j=0; j < numNodes;j++){
                    currNext = curr.next;
				    curr.next = reverse;
				    reverse = curr;
				    curr = currNext;
                }

				// updating the prev pointer after reversal of the even group
                prevNext = prev.next;
				prev.next = node;
			    prev = prevNext;
			}

			// increment 1 by one and move to the next group
			groupLen += 1;
		}
	    return head;
	}
		

	public static void main(String[] args) {
	    // Declaring and creating a linked list
      ReverseKGroups.LinkedList1 obj1 = new ReverseKGroups.LinkedList1();
      int[] inputArray1 = {1, 2, 3, 4};
      int[] inputArray2 = {10, 11, 12, 13, 14};
      int[] inputArray3 = {15};
      int[] inputArray4 = {16, 17};

      ReverseKGroups.LinkedList1 inputLinkList1 = new ReverseKGroups.LinkedList1();
      inputLinkList1.createLinkedList(inputArray1);

        ReverseKGroups.LinkedList1 inputLinkList2 = new ReverseKGroups.LinkedList1();
      inputLinkList2.createLinkedList(inputArray2);

        ReverseKGroups.LinkedList1 inputLinkList3 = new ReverseKGroups.LinkedList1();
      inputLinkList3.createLinkedList(inputArray3);

        ReverseKGroups.LinkedList1 inputLinkList4 = new ReverseKGroups.LinkedList1();
      inputLinkList4.createLinkedList(inputArray4);

      ReverseKGroups.LinkedListNode[] listHeads = {inputLinkList1.head, inputLinkList2.head, inputLinkList3.head, inputLinkList4.head};

      for (int i = 0; i < listHeads.length; i++) {
          System.out.println(i + 1 + ".\tIf we reverse the even length groups of the linked list:");
          ReverseKGroups.PrintList.printListWithForwardArrow(listHeads[i]);
          System.out.println("\n\n\twe will get: \t");
          ReverseKGroups.PrintList.printListWithForwardArrow(reverseEvenLengthGroups(listHeads[i]));
          System.out.print("\n");
          System.out.println(new String(new char[100]).replace('\0', '-'));
      }
	 }

    public static class ReverseLLinEvenGroups {

        public ListNode reverseEvenLengthGroups(ListNode head) {

            int groupLen = 2;
            int numNodes = 0;

            ListNode prev = head;
            ListNode node, curr, currNext, prevNext, reverse = null;

            while (prev.next != null) {

                numNodes = 0;
                node = prev;

                // traverse all elements in current group

                for (int i = 0; i < groupLen; i++) {
                    if (node.next == null) {
                        break;
                    }
                    numNodes++;
                    node = node.next;
                }

                // odd len groups skip
                if (numNodes % 2 != 0) {
                    prev = node;
                }

                // even len group reverse
                else {

                    reverse = node.next;
                    curr = prev.next;
                    // reverse the group
                    for (int i = 0; i < numNodes; i++) {
                        currNext = curr.next;
                        curr.next = reverse;
                        reverse = curr;
                        curr = currNext;
                    }

                    prevNext = prev.next;
                    prev.next = node;
                    prev = prevNext;

                }

                groupLen++;

            }

            return head;

        }

        public static class ListNode {
            int val;
            ListNode next;

            ListNode() {
            }

            ListNode(int val) {
                this.val = val;
            }

            ListNode(int val, ListNode next) {
                this.val = val;
                this.next = next;
            }
        }
    }
}



