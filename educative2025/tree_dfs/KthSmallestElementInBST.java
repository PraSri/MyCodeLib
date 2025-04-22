package tree_dfs;

import BinarySearchTrees.TreeNode;

import java.util.LinkedList;

public class KthSmallestElementInBST {

    public static void main(String[] args) {

    }

    public int kthsmallest(BinarySearchTrees.TreeNode A, int B) {

        return helper(A, B);

    }

    private int helper(BinarySearchTrees.TreeNode root, int k) {
        if (size(root.left) == k - 1) {
            return root.val;
        }
        if (size(root.left) >= k) {
            return helper(root.left, k);
        }
        return helper(root.right, k - 1 - size(root.left));
    }

    private int size(BinarySearchTrees.TreeNode root) {
        if (root == null)
            return 0;
        return size(root.left) + size(root.right) + 1;
    }

    public int kthSmallestIterative(BinarySearchTrees.TreeNode root, int k) {

        LinkedList<BinarySearchTrees.TreeNode> stack = new LinkedList<>();

        while (true) {

            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            k--;
            if (k == 0) {
                return root.val;
            }
            root = root.right;
        }

    }

    private int count = 0;
    private int result = -1;

    public int kthSmallest(BinarySearchTrees.TreeNode root, int k) {
        inorder(root, k);
        return result;
    }

    private void inorder(TreeNode node, int k) {
        if (node == null) return;

        inorder(node.left, k);

        count++;
        if (count == k) {
            result = node.val;
            return;
        }

        inorder(node.right, k);
    }

}
