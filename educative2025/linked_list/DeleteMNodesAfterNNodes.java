package linked_list;

import linked_list.ReverseKGroups.LinkedListNode;

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
}
