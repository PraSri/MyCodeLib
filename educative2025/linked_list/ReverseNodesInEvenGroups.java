import java.util.*;

class ReverseNodesInEvenGroups {
	public static LinkedListNode reverseEvenLengthGroups(LinkedListNode head)
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
      LinkedList obj1 = new LinkedList();
      int[] inputArray1 = {1, 2, 3, 4};
      int[] inputArray2 = {10, 11, 12, 13, 14};
      int[] inputArray3 = {15};
      int[] inputArray4 = {16, 17};

      LinkedList inputLinkList1 = new LinkedList();
      inputLinkList1.createLinkedList(inputArray1);

      LinkedList inputLinkList2 = new LinkedList();
      inputLinkList2.createLinkedList(inputArray2);

      LinkedList inputLinkList3 = new LinkedList();
      inputLinkList3.createLinkedList(inputArray3);

      LinkedList inputLinkList4 = new LinkedList();
      inputLinkList4.createLinkedList(inputArray4);

      LinkedListNode[] listHeads = {inputLinkList1.head, inputLinkList2.head, inputLinkList3.head, inputLinkList4.head};

      for (int i = 0; i < listHeads.length; i++) {
          System.out.println(i + 1 + ".\tIf we reverse the even length groups of the linked list:");
          PrintList.printListWithForwardArrow(listHeads[i]);
          System.out.println("\n\n\twe will get: \t");
          PrintList.printListWithForwardArrow(reverseEvenLengthGroups(listHeads[i]));
          System.out.print("\n");
          System.out.println(new String(new char[100]).replace('\0', '-'));
      }
	 }
}
