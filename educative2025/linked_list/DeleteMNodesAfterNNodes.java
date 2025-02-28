import java.util.List;
import java.util.ArrayList;

public class DeleteMNodesAfterNNodes {
    public static LinkedListNode deleteNodes(LinkedListNode head, int m, int n) {
        
        // Set current node to the head of the linked list
        LinkedListNode current = head;
        
        // Pointer to the last node to keep during the m count
        LinkedListNode lastMNode = null;

        // Start the traversal
        while (current != null) {
          
            // Keep the next m nodes 
            int mCount = m;
            while (current != null && mCount > 0) {
              
                // Update the last node to keep
                lastMNode = current;
                
                // Move to the next node
                current = current.next;
                mCount--;
            }

            // Skip the next n nodes
            int nCount = n;
            while (current != null && nCount > 0) {
                
                // Move to the next node
                current = current.next;
                nCount--;
            }

            // Connect the last kept node to the next non-skipped node
            if (lastMNode != null) {
                lastMNode.next = current;
            }
        }

        // Return the modified linked list starting from the head
        return head;
    }

    // Driver code
    public static void main(String[] args) {
        int[][] inputLists = {
            {5},
            {1, 2, 2, 3, 3, 3},
            {3, 7, 9},
            {10, 10, 100, 100, 100},
            {7, 7, 7, 7, 77, 77, 77, 77}
        };

        int[] allMs = {1, 3, 1, 4, 5};
        int[] allNs = {1, 1, 3, 2, 7};

        for (int i = 0; i < inputLists.length; i++) {
            LinkedList inputLinkedList = new LinkedList();
            inputLinkedList.createLinkedList(inputLists[i]);

            System.out.println((i + 1) + ".\tInput:");
            System.out.print("\t");
            inputLinkedList.printListWithForwardArrow();
            System.out.println("\tm = " + allMs[i]);
            System.out.println("\tn = " + allNs[i]);

            inputLinkedList.head = deleteNodes(inputLinkedList.head, allMs[i], allNs[i]);
            System.out.println("\n\tOutput:");
            System.out.print("\t");
            inputLinkedList.printListWithForwardArrow();
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
