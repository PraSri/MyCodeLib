package linked_list;

public class InsertIntoASortedCircularLinkedList {
    static class Node {
        int val;
        Node next;

        // Constructor
        public Node(int val) {
            this.val = val;
            this.next = null;
        }

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    public Node insert(Node head, int val) {
        if (head == null) {
            Node node = new Node(val);
            node.next = node;
            return node;
        }
        Node prev = head;
        Node curr = head.next;
        boolean flag = false;
        while (true) {
            // case 1: insert between 2 sorted nodes, normal case
            if (prev.val <= val && val <= curr.val) {
                flag = true;
            }
            // case 2: at the rotation point (max -> min)
            else if (prev.val > curr.val) {
                if (val >= prev.val || val <= curr.val)
                    flag = true;
            }

            if (flag) {
                prev.next = new Node(val, curr);
                return head;
            }
            prev = curr;
            curr = curr.next;
            if (prev == head) {
                break;
            }
        }

        // case 3: no valid point found, insert at any place
        prev.next = new Node(val, curr);
        return head;
    }
}
