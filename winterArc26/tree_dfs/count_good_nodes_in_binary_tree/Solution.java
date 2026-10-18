package tree_dfs.count_good_nodes_in_binary_tree;

import common.TreeNode;

public class Solution {

    public int goodNodes(TreeNode root) {
        return count(root, Integer.MIN_VALUE);
    }

    private int count(TreeNode node, int maxSoFar) {
        if (node == null) {
            return 0;
        }
        int good = node.val >= maxSoFar ? 1 : 0;
        int nextMax = Math.max(maxSoFar, node.val);
        return good + count(node.left, nextMax) + count(node.right, nextMax);
    }
}
