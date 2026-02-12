package linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * Clone Graph
 * https://leetcode.com/problems/clone-graph/
 * <p>
 * Clone Binary Tree With Random Pointer
 * https://leetcode.com/problems/clone-binary-tree-with-random-pointer/
 * <p>
 * Clone N-ary Tree
 * https://leetcode.com/problems/clone-n-ary-tree/
 */

public class CopyLinkedListWithRandomPointer {
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node temp = head;
        while (temp != null) {
            Node newNode = new Node(temp.val);
            map.put(temp, newNode);
            temp = temp.next;
        }
        temp = head;
        while (temp != null) {
            Node copyNode = map.get(temp);
            copyNode.next = map.get(temp.next);
            copyNode.random = map.get(temp.random);
            temp = temp.next;
        }
        return map.get(head);
    }

    /**
     * Space O(1)
     * The algorithm is composed of the follow three steps which are also 3 iteration rounds.
     * <p>
     * Iterate the original list and duplicate each node. The duplicate
     * of each node follows its original immediately.
     * Iterate the new list and assign the random pointer for each
     * duplicated node.
     * Restore the original list and extract the duplicated nodes.
     */

    public Node copyRandomList2(Node head) {
        Node iter = head, next;

        // First round: make copy of each node,
        // and link them together side-by-side in a single list.
        while (iter != null) {
            next = iter.next;

            Node copy = new Node(iter.val);
            iter.next = copy;
            copy.next = next;

            iter = next;
        }

        // Second round: assign random pointers for the copy nodes.
        iter = head;
        while (iter != null) {
            if (iter.random != null) {
                iter.next.random = iter.random.next;
            }
            iter = iter.next.next;
        }

        // Third round: restore the original list, and extract the copy list.
        iter = head;
        Node pseudoHead = new Node(0);
        Node copy, copyIter = pseudoHead;

        while (iter != null) {
            next = iter.next.next;

            // extract the copy
            copy = iter.next;
            copyIter.next = copy;
            copyIter = copy;

            // restore the original list
            iter.next = next;

            iter = next;
        }

        return pseudoHead.next;
    }

    public static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * <a href="https://leetcode.com/problems/clone-graph/">LeetCode - Clone Graph</a>
     */
    public static class CloneGraph {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/clone-binary-tree-with-random-pointer/">LeetCode - Clone Binary Tree With Random Pointer</a>
     */
    public static class CloneBinaryTreeWithRandomPointer {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/clone-n-ary-tree/">LeetCode - Clone N-ary Tree</a>
     */
    public static class CloneNAryTree {
        // placeholder
    }

}
