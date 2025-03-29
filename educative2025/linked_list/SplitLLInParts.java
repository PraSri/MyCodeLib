package linked_list;

import linked_list.ReverseKGroups.LinkedListNode;

public class SplitLLInParts {

    // Function to split the linked list into k parts
    public static LinkedListNode[] splitListToParts(LinkedListNode head, int k) {
        LinkedListNode[] ans = new LinkedListNode[k];

        // Calculate the total size of the linked list
        int size = 0;
        LinkedListNode current = head;
        while (current != null) {
            size++;
            current = current.next;
        }

        // Calculate the base size of each part
        int split = size / k;

        // Calculate the number of extra nodes to be distributed among the first few parts
        int remaining = size % k;

        current = head;
        LinkedListNode prev = null;
        for (int i = 0; i < k; i++) {
            // Start a new part
            ans[i] = current;

            // Determine the size of the current part
            int currentSize = split + (remaining > 0 ? 1 : 0);
            if (remaining > 0) remaining--;

            // Traverse the current part to its end
            for (int j = 0; j < currentSize; j++) {
                prev = current;
                if (current != null) {
                    current = current.next;
                }
            }

            // Disconnect the current part from the rest of the list
            if (prev != null) {
                prev.next = null;
            }
        }

        return ans;
    }


}
