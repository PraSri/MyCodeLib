package DynamicProgramming;

public class MaxSumPathInBinaryTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	int ans = Integer.MIN_VALUE;

	public int maxPathSum(TreeNode A, boolean t) {
		if (A == null) {
			return 0;
		}

		int l = Math.max(0, maxPathSum(A.left, true));
		int r = Math.max(0, maxPathSum(A.right, true));

		ans = Math.max(ans, A.val + l + r);

		return A.val + Math.max(l, r);
	}

	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
			left = null;
			right = null;
		}
	}
}
