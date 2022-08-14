package BinarySearchTrees;

public class LCA_of_BST {

    /**
     * Find the lowest common ancestor of two nodes A and B given the root node
     *
     * @param root
     * @param nodeA
     * @param nodeB
     * @return lowest common ancestor
     */
    static TreeNode LCA(TreeNode root, TreeNode nodeA, TreeNode nodeB) {
        if (root == null) {
            return null;
        }

        if (nodeA == root || nodeB == root) {
            return root;
        }

        TreeNode left = LCA(root.left, nodeA, nodeB);

        TreeNode right = LCA(root.right, nodeA, nodeB);

        if (left != null && right != null) {
            return root;
        }

        if (left == null) {
            return right;
        } else
            return left;
    }

    static TreeNode LCA_v2(TreeNode root, TreeNode nodeA, TreeNode nodeB) {
        if (root == null) {
            return null;
        }

        if (root.val > nodeA.val && root.val > nodeB.val) {
            return LCA(root.left, nodeA, nodeB);
        }

        if (root.val < nodeA.val && root.val < nodeB.val) {
            return LCA(root.right, nodeA, nodeB);
        }

        return root;
    }

}
