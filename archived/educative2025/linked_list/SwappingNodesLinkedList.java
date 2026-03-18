package linked_list;

import linked_list.ReverseKGroups.LinkedListNode;

public class SwappingNodesLinkedList {
    public static void swap(LinkedListNode node1, LinkedListNode node2) {
        int temp = node1.data;
        node1.data = node2.data;
        node2.data = temp;
    }

    public static LinkedListNode swapNodes(LinkedListNode head, int k) {
        if (head == null) {
            return head;
        }
        int count = 0;

        // front and end pointers will be used to track the kth node from
        // the start and end of the linked list, respectively
        LinkedListNode front = null;
        LinkedListNode end = null;
        LinkedListNode curr = head;

        while (curr != null) {
            count += 1;
            // if end is not null, it means the kth node from the beginning has
            // been found, we can start moving the end pointer to find the
            // kth node from the end of the linked list
            if (end != null) {
                end = end.next;
            }
            // if the count has become equal to k, it means the curr is
            // pointing the kth node at the begining of the linked list
            if (count == k) {
                front = curr;
                end = head;
            }
            curr = curr.next;

        }
        // swap the values of two nodes
        swap(front, end);
        return head;
    }

}
