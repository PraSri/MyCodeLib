package trees;

public class InvertBinaryTree {

    // Reverse Odd Levels of Binary Tree - https://leetcode.com/problems/reverse-odd-levels-of-binary-tree/

    public TreeNode invertTree(TreeNode root) {

        if(root == null) {
            return root;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        root.left = invertTree(root.left);
        root.right = invertTree(root.right);

        return root;
    }


    /**
     * Reverse Odd Levels of Binary Tree
     * https://leetcode.com/problems/reverse-odd-levels-of-binary-tree/
     */
    public static class ReverseOddLevelsOfBinaryTree {
    }
}
