package linked_list;// Template for the linked list

public class ReverseKGroups {

    public static LinkedListNode reverseKGroups(LinkedListNode head, int k) {

        LinkedListNode dummy = new LinkedListNode(0);
        dummy.next = head;
        LinkedListNode ptr = dummy;

        while (ptr != null) {

            LinkedListNode tracker = ptr;

            for (int i = 0; i < k; i++) {
                if (tracker == null) {
                    break;
                }
                tracker = tracker.next;
            }

            if (tracker == null) {
                break;
            }

            LinkedListNode[] updatedNodes = LinkedListReversal.reverseLinkedList(ptr.next, k);
            LinkedListNode previous = updatedNodes[0];
            LinkedListNode current = updatedNodes[1];

            LinkedListNode lastNodeOfReversedGroup = ptr.next;
            lastNodeOfReversedGroup.next = current;
            ptr.next = previous;
            ptr = lastNodeOfReversedGroup;

        }

        return dummy.next;
    }

    // Driver code
    public static void main(String[] args) {
        int[][] inputList = {
                {1, 2, 3, 4, 5, 6, 7, 8},
                {3, 4, 5, 6, 2, 8, 7, 7},
                {1, 2, 3, 4, 5},
                {1, 2, 3, 4, 5, 6, 7},
                {1}
        };

        int[] k = {3, 2, 1, 7, 1};

        for (int i = 0; i < inputList.length; ++i) {
            LinkedList inputLinkedList = new LinkedList();
            inputLinkedList.createLinkedList(inputList[i]);

            System.out.print((i + 1) + ".\tLinked list: ");
            PrintList.printListWithForwardArrow(inputLinkedList.head);
            System.out.println();

            System.out.print("\n\tReversed linked list: ");
            LinkedListNode result = reverseKGroups(inputLinkedList.head, k[i]);
            PrintList.printListWithForwardArrow(result);
            System.out.println();

            String hyphens = new String(new char[100]).replace('\0', '-');
            System.out.println(hyphens);
        }
    }

    static class LinkedList<T> {
        public LinkedListNode head;

        // constructor will be used to make a LinkedList type object
        public LinkedList() {
            this.head = null;
        }

        // insertNodeAtHead method will insert a LinkedListNode at head
        // of a linked list.
        public void insertNodeAtHead(LinkedListNode node) {
            if (this.head == null) {
                this.head = node;
            } else {
                node.next = this.head;
                this.head = node;
            }
        }

        // createLinkedList method will create the linked list using the
        // given integer array with the help of InsertAthead method.
        public void createLinkedList(int[] lst) {
            for (int i = lst.length - 1; i >= 0; i--) {
                LinkedListNode newNode = new LinkedListNode(lst[i]);
                insertNodeAtHead(newNode);
            }
        }
    }

    // Template for printing the linked list with forward arrows
    static class PrintList {
        public static void printListWithForwardArrow(LinkedListNode head) {
            LinkedListNode temp = head;

            while (temp != null) {
                System.out.print(temp.data); // print node value
                temp = temp.next;
                if (temp != null) {
                    System.out.print(" → ");
                }
            }
            // if this is the last node, print null at the end
            System.out.print(" → null ");
        }
    }

// Template for the linked list
    static class LinkedList<T> {
        public LinkedListNode head;

        // constructor will be used to make a LinkedList type object
        public LinkedList() {
            this.head = null;
        }

        // insertNodeAtHead method will insert a LinkedListNode at head
        // of a linked list.
        public void insertNodeAtHead(LinkedListNode node) {
            if (this.head == null) {
                this.head = node;
            } else {
                node.next = this.head;
                this.head = node;
            }
        }

        // createLinkedList method will create the linked list using the
        // given integer array with the help of InsertAthead method.
        public void createLinkedList(int[] lst) {
            for (int i = lst.length - 1; i >= 0; i--) {
                LinkedListNode newNode = new LinkedListNode(lst[i]);
                insertNodeAtHead(newNode);
            }
        }
    }

    static class LinkedListReversal {
        static LinkedListNode[] reverseLinkedList(LinkedListNode node, int k) {

            LinkedListNode previous = null;
            LinkedListNode current = node;
            LinkedListNode next = null;

            for (int i = 0; i < k; i++) {
                next = current.next;
                current.next = previous;
                previous = current;
                current = next;
            }

            return new LinkedListNode[]{previous, current};
        }
    }
}
