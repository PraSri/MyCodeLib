package trees;

public class CountGoodNodesInBinaryTree {

    //Within a binary tree, a node x is considered good if the path from the root of the tree
    // to the node x contains no nodes with a value greater than the value of node x.
    //Given the root of a binary tree root, return the number of good nodes within the tree.

    public int goodNodes(TreeNode root) {
        return goodNodes(root, Integer.MIN_VALUE);
    }

    private int goodNodes(TreeNode root, int max) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        if (root.val >= max) {
            res += 1;
        }
        res += goodNodes(root.left, Math.max(max, root.val));
        res += goodNodes(root.right, Math.max(max, root.val));
        return res;
    }

}
