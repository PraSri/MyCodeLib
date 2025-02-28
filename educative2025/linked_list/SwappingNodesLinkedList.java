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

    public static void main(String[] args) {
       
        int[][] input = {
            {1, 2, 3, 4, 5, 6, 7},
            {6, 9, 3, 10, 7, 4, 6},
            {6, 9, 3, 4},
            {6, 2, 3, 6, 9},
            {6, 2}
        };
        int[] k = {
            2, 3, 2, 3, 1
        };

        for(int i=0; i<input.length; i++){
            System.out.print(i+1);
            LinkedList<Integer> list = new LinkedList<Integer>();
            list.createLinkedList(input[i]);
            System.out.print(".\tOriginal linked list is: ");
            PrintList.printListWithForwardArrow(list.head);
            System.out.println("\tk: "+k[i]);
            System.out.print("\tLinked list with swapped values: ");
            PrintList.printListWithForwardArrow(swapNodes(list.head,k[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
