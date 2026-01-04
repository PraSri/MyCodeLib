package trees;

/**Maximum Depth of Binary Tree (Easy)
👉 https://leetcode.com/problems/maximum-depth-of-binary-tree/

K-th Largest Perfect Subtree Size in Binary Tree (Medium)
👉 https://leetcode.com/problems/k-th-largest-perfect-subtree-size-in-binary-tree/

Check Balanced String (Easy)
👉 https://leetcode.com/problems/check-balanced-string/*/

public class BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        if(true) {
            return dfs(root)[0]==1;
        }
        if(root==null)
            return true;
        int l = height(root.left);
        int r = height(root.right);
        if(Math.abs(l-r)>1)
            return false;
        return isBalanced(root.left) && isBalanced(root.right);
    }

    private int height(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int ls = height(root.left);
        int rs = height(root.right);
        return 1 + Math.max(ls, rs);
    }

    // int[] array with 2 element
    // 0th index -> isBalanced(0,1)
    // 1st index -> height
    private int[] dfs(TreeNode root) {
        if(root == null) {
            return new int[]{1, 0}; // isBalanced true & height = 0
        }
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        // if left subtree is balanced and right subtree is balanced and
        // diff between heights of left subtree and right subtree is less than equal to 1
        boolean balanced = (left[0]==1 && right[0]==1) && (Math.abs(left[1]-right[1])<=1);
        int height = 1 + Math.max(left[1], right[1]);
        return new int[]{balanced?1:0, height};
    }

    /**
     * Maximum Depth of Binary Tree
     * https://leetcode.com/problems/maximum-depth-of-binary-tree/
     */
    public static class MaximumDepthOfBinaryTree {
    }

    /**
     * K-th Largest Perfect Subtree Size in Binary Tree
     * https://leetcode.com/problems/k-th-largest-perfect-subtree-size-in-binary-tree/
     */
    public static class KThLargestPerfectSubtreeSizeInBinaryTree {
    }

    /**
     * Check Balanced String
     * https://leetcode.com/problems/check-balanced-string/
     */
    public static class CheckBalancedString {
    }
}
