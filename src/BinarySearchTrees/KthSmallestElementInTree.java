package BinarySearchTrees;

public class KthSmallestElementInTree {

	public static void main(String[] args) {

	}

	public int kthsmallest(TreeNode A, int B) {

		return helper(A, B);

	}

	private int helper(TreeNode root, int k) {
		if (size(root.left) == k - 1) {
			return root.val;
		}
		if (size(root.left) >= k) {
			return helper(root.left, k);
		}
		return helper(root.right, k - 1 - size(root.left));
	}

	private int size(TreeNode root) {
		if (root == null)
			return 0;
		return size(root.left) + size(root.right) + 1;
	}

}
