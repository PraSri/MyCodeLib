package bst.valid_binary_search_tree;

import common.TreeNode;

public class Solution {

    public boolean isValidBST(TreeNode root) {
        return valid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean valid(TreeNode node, long low, long high) {
        if (node == null) {
            return true;
        }
        if (node.val <= low || node.val >= high) {
            return false;
        }
        return valid(node.left, low, node.val) && valid(node.right, node.val, high);
    }
}
