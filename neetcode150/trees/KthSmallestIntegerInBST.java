package trees;

import BinarySearchTrees.KthSmallestElementInTree;

import java.util.Stack;

/**
 * Binary Tree Inorder Traversal
 * https://leetcode.com/problems/binary-tree-inorder-traversal/
 * <p>
 * Second Minimum Node In a Binary Tree
 * https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/
 * <p>
 * Binary Tree Inorder Traversal
 * https://leetcode.com/problems/binary-tree-inorder-traversal/
 * <p>
 * Second Minimum Node In a Binary Tree
 * https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/
 */

public class KthSmallestIntegerInBST {
    private int recursiveSol = new KthSmallestElementInTree().kthSmallest(null, 0);

    public int kthSmallest(TreeNode root, int k) {
        if (root == null) {
            return -1;
        }
        Stack<TreeNode> s = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !s.isEmpty()) {
            while (curr != null) {
                s.push(curr);
                curr = curr.left;
            }
            curr = s.pop();
            k--;
            if (k == 0) {
                return curr.val;
            }
            curr = curr.right;
        }
        return -1;
    }


    /**
     * Binary Tree Inorder Traversal
     * https://leetcode.com/problems/binary-tree-inorder-traversal/
     */
    public static class BinaryTreeInorderTraversal {
    }

    /**
     * Second Minimum Node In a Binary Tree
     * https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/
     */
    public static class SecondMinimumNodeInABinaryTree {
    }
}
