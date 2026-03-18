package tree_bfs;

import Trees.TreeNode;

import java.util.*;

public class VerticalOrderTraversal {

    public static void main(String[] args) {

    }

    // https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
    public static class Pair {
        TreeNode node;
        int hd; // hd refers to horizontal distance, for root it is 0, for left child it is -1 & right child it is +1

        public Pair(TreeNode node, int hd) {
            this.node = node;
            this.hd = hd;
        }

    }

    public List<List> verticalOrderTraversal(TreeNode A) {

        List<List> res = new LinkedList<>();

        if (A == null) {
            return new ArrayList<>();
        }

        // map of column hd & its nodes
        Map<Integer, List<TreeNode>> m = new TreeMap<>();

        Queue<Pair> q = new LinkedList<>();

        Pair rootPair = new Pair(A, 0);

        q.add(rootPair);

        while (!q.isEmpty()) {

            Pair p = q.poll();

            int hd = p.hd;
            TreeNode node = p.node;

            if (!m.containsKey(hd)) {
                List<TreeNode> l = new LinkedList<>();
                l.add(node);
                m.put(hd, l);
            } else {
                m.get(hd).add(node);
            }

            if (node.left != null) {
                q.add(new Pair(node.left, hd - 1));
            }
            if (node.right != null) {
                q.add(new Pair(node.right, hd + 1));
            }

        }
        for (Map.Entry<Integer, List<TreeNode>> me : m.entrySet()) {

            List l = new LinkedList<>();

            for (TreeNode t : me.getValue()) {
                l.add(t.val);
            }
            Collections.sort(l);
            res.add(l);

        }

        return res;

    }

    public static class Solution {

        public static List<List<Integer>> verticalOrder(TreeNode root) {
            List<List<Integer>> output = new ArrayList<>();
            if (root == null) {
                return output;
            }

            Map<Integer, ArrayList<Integer>> nodeList = new HashMap<>();

            Queue<Map.Entry<TreeNode, Integer>> queue = new ArrayDeque<>();

            int column = 0;
            queue.offer(new AbstractMap.SimpleEntry<>(root, column));

            int minColumn = 0;
            int maxIndex = 0;

            while (!queue.isEmpty()) {
                Map.Entry<TreeNode, Integer> p = queue.poll();
                root = p.getKey();
                column = p.getValue();

                if (root != null) {
                    if (!nodeList.containsKey(column)) {
                        nodeList.put(column, new ArrayList<>());
                    }
                    nodeList.get(column).add(root.val);
                    minColumn = Math.min(minColumn, column);
                    maxIndex = Math.max(maxIndex, column);

                    queue.offer(new AbstractMap.SimpleEntry<>(root.left, column - 1));
                    queue.offer(new AbstractMap.SimpleEntry<>(root.right, column + 1));
                }
            }

            for (int i = minColumn; i < maxIndex + 1; ++i) {
                output.add(nodeList.get(i));
            }
            return output;
        }
    }
}
