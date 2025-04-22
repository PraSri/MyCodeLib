package tree_dfs;

import java.util.*;

public class LCA {

    private TreeNode<Integer> lca;

    public LCA() {
        this.lca = null;
    }

    public TreeNode<Integer> lowestCommonAncestor(TreeNode<Integer> root, TreeNode<Integer> p, TreeNode<Integer> q) {
        lowestCommonAncestorRec(root, p, q);
        return lca;
    }

    // helper function to find the lowest common ancestor recursively
    private boolean lowestCommonAncestorRec(TreeNode<Integer> currentNode, TreeNode<Integer> p, TreeNode<Integer> q) {
        // if currentNode does not exist
        if (currentNode == null)
            return false;

        // initialize tracking variables
        boolean left = false, right = false, mid = false;

        // check if either of the input nodes is the currentNode
        if (p == currentNode || q == currentNode)
            mid = true;

        // traverse binary tree using depth-first search
        left = lowestCommonAncestorRec(currentNode.left, p, q);

        // if the lowest common ancestor has not been found, only then traverse the right subtree
        if (lca == null)
            right = lowestCommonAncestorRec(currentNode.right, p, q);

        // if any two of the tracking variables are true, set currentNode as answer node
        if (boolToInt(mid) + boolToInt(left) + boolToInt(right) >= 2)
            lca = currentNode;

        // return true if any of the tracking variables is true
        return mid || left || right;
    }

    private int boolToInt(boolean val) {
        return (val) ? 1 : 0;
    }
}