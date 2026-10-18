package tree_dfs.subtree_of_another_tree;

import common.TreeNode;

public class Solution {

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (subRoot == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        return isSame(root, subRoot) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private boolean isSame(TreeNode a, TreeNode b) {
        if (a == null || b == null) {
            return a == b;
        }
        return a.val == b.val && isSame(a.left, b.left) && isSame(a.right, b.right);
    }
}
