package trees;

/**Binary Tree Inorder Traversal (Easy)
https://leetcode.com/problems/binary-tree-inorder-traversal/

Find Mode in Binary Search Tree (Easy)
https://leetcode.com/problems/find-mode-in-binary-search-tree/*/

public class ValidBinarySearchTree {

    public boolean isValidBST(TreeNode root) {
        return isValid(root, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    boolean isValid(TreeNode root, int max, int min) {
        if(root == null) {
            return true;
        }
        if(root.val >= max || root.val <= min) {
            return false;
        }
        return isValid(root.left, root.val, min) && isValid(root.right, max, root.val);
    }


    /**
     * Binary Tree Inorder Traversal
     * https://leetcode.com/problems/binary-tree-inorder-traversal/
     */
    public static class BinaryTreeInorderTraversal {
    }

    /**
     * Find Mode in Binary Search Tree
     * https://leetcode.com/problems/find-mode-in-binary-search-tree/
     */
    public static class FindModeInBinarySearchTree {
    }
}
