import java.util.ArrayList;
import java.util.List;

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


    public static void main(String[] args) {
        int[][] lists = {
            {9, 7, 8, 7, 7, 6},
            {2, 3, 5, 7, 11},
            {4, 4, 4, 4, 4},
            {1, 2, 3, 11, 22, 33},
            {1, 2, 6, 3, 4, 5, 6}
        };

        int[] ks = {7, 2, 4, 3, 6};

        for (int i = 0; i < lists.length; i++) {
            LinkedList inputLinkedList = new LinkedList();
            inputLinkedList.createLinkedList(lists[i]);
            System.out.print((i + 1) + ". \tLinked list: ");
            PrintList.printListWithForwardArrow(inputLinkedList.head);

            System.out.println("\n\tk: " + ks[i]);
            LinkedListNode[] result = splitListToParts(inputLinkedList.head, ks[i]);

            System.out.print("\tLinked list parts: [");
            for (int j = 0; j < result.length; j++) {
                if (result[j] != null) {
                    System.out.print("[");
                    LinkedListNode part = result[j];
                    while (part != null) {
                        System.out.print(part.data);
                        if (part.next != null) System.out.print(", ");
                        part = part.next;
                    }
                    System.out.print("]");
                } else {
                    System.out.print("[]");
                }
                if (j < result.length - 1) System.out.print(", ");
            }
            System.out.println("]");
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
