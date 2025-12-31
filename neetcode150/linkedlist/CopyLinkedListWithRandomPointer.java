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
