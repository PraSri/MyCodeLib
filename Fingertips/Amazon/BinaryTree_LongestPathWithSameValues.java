package Amazon;

import Trees.TreeNode;

public class BinaryTree_LongestPathWithSameValues {

	public static void main(String[] args) {

	}

	private static int ans = 0;

	public int longestUnivaluePath(TreeNode root) {

		ans = 0;
		length(root);
		return ans;

	}

	private int length(TreeNode node) {

		if (node == null)
			return 0;

		int l = length(node.left);
		int r = length(node.right);

		int lmax = 0, rmax = 0;

		if (node.left != null && node.left.val == node.val) {
			lmax += l + 1;
		}

		if (node.right != null && node.right.val == node.val) {
			rmax += r + 1;
		}

		ans = Math.max(ans, lmax + rmax);

		return Math.max(lmax, rmax);

	}

}
