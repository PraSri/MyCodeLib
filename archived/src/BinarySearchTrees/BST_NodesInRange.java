package BinarySearchTrees;

public class BST_NodesInRange {

	public static void main(String[] args) {

	}

	public int solve(TreeNode A, int B, int C) {
		return helper(A, B, C);
	}

	private int helper(TreeNode root, int l, int r) {

		if (root == null) {
			return 0;
		}
		if (root.val >= l && root.val <= r) {
			return 1 + helper(root.left, l, r) + helper(root.right, l, r);
		}
		if (root.val < l)
			return helper(root.right, l, r);
		else {
			return helper(root.left, l, r);
		}

	}

}
