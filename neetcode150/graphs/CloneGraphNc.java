package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copy List with Random Pointer (Medium)
 * https://leetcode.com/problems/copy-list-with-random-pointer/
 * <p>
 * Clone Binary Tree With Random Pointer (Medium)
 * https://leetcode.com/problems/clone-binary-tree-with-random-pointer/
 * <p>
 * Clone N-ary Tree (Medium)
 * https://leetcode.com/problems/clone-n-ary-tree/
 */

public class CloneGraphNc {

    public Node cloneGraph(Node node) {
        Map<Integer, Node> map = new HashMap<>();
        return dfs(node, map);
    }

    private Node dfs(Node src, Map<Integer, Node> map) {

        if (src == null) {
            return src;
        }

        if (map.containsKey(src.val)) {
            return map.get(src.val);
        }

        Node node = new Node(src.val);
        map.put(node.val, node);

        for (Node neighbour : src.neighbors) {
            node.neighbors.add(dfs(neighbour, map));
        }
        return node;
    }

    /**
     * Copy List with Random Pointer
     * https://leetcode.com/problems/copy-list-with-random-pointer
     */

    public static class CopyListWithRandomPointer {
        // space O(1)
        // 1. iterate the original list and create duplicate node next to original node
        // 2. iterate new list and assign random pointer for each duplicated node
        // 3. restore the original list and extract the duplicate list

        public Node copyRandomList(Node head) {
            Node curr = head, next;
            while (curr != null) {
                next = curr.next;
                Node copy = new Node(curr.val);
                curr.next = copy;
                copy.next = next;
                curr = next;
            }
            curr = head;
            while (curr != null) {
                if (curr.random != null) {
                    curr.next.random = curr.random.next;
                }
                curr = curr.next.next;
            }
            curr = head;
            Node dummyHead = new Node(-1);
            Node copy, copyCurr = dummyHead;
            while (curr != null) {
                next = curr.next.next;

                // extract copy
                copy = curr.next;
                copyCurr.next = copy;
                copyCurr = copy;

                // restore current
                curr.next = next;

                curr = next;
            }
            return dummyHead.next;
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

    }

    /**
     * Clone Binary Tree With Random Pointer
     * https://leetcode.com/problems/clone-binary-tree-with-random-pointer
     */
    public static class CloneBinaryTreeWithRandomPointer {

        static Node cloneTree(Node root) {
            if (root == null) {
                return null;
            }
            // map to store mapping between original and cloned node
            Map<Node, Node> map = new HashMap<>();
            // copyLeftRightNode
            Node newRoot = copyLeftRightNode(root, map);
            // copyRandomPointer
            copyRandom(root, map);
            // return new tree
            return newRoot;
        }

        private static void copyRandom(Node root, Map<Node, Node> map) {
            if (root == null) {
                return;
            }

            map.get(root).random = map.get(root.random);
            //recur
            copyRandom(root.left, map);
            copyRandom(root.right, map);

        }

        private static Node copyLeftRightNode(Node root, Map<Node, Node> map) {
            if (root == null) {
                return null;
            }
            Node clonedNode = new Node(root.x);
            map.put(root, clonedNode);
            // recursive
            clonedNode.left = copyLeftRightNode(root.left, map);
            clonedNode.right = copyLeftRightNode(root.right, map);

            return clonedNode;
        }

        static class Node {
            Node left, right, random;
            int x;

            public Node(int x) {
                this.x = x;
                left = right = random = null;
            }
        }

    }

    /**
     * Clone N-ary Tree
     * https://leetcode.com/problems/clone-n-ary-tree
     */
    public static class CloneNAryTree {
        public Node cloneNAryTree(Node root) {
            if (root == null) {
                return null;
            }
            List<Node> clonedChildren = new ArrayList<>();
            //recur
            for (Node child : root.children) {
                clonedChildren.add(cloneNAryTree(child));
            }
            return new Node(root.x, clonedChildren);
        }

        public static class Node {
            int x;
            List<Node> children;

            public Node(int x, List<Node> children) {
                this.x = x;
                this.children = new ArrayList<>();
                this.children.addAll(children);
            }
        }
    }
}