package BinarySearchTrees;

public class LargestBSTSubtree {

	public static void main(String[] args) {

	}

	public class Data {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		int size = 0;
		int ans = 0;
		boolean isBST = true;

		public Data(int min, int max, int size, int ans, boolean isBST) {
			this.min = min;
			this.max = max;
			this.size = size;
			this.ans = ans;
			this.isBST = isBST;
		}

		public Data(int ans, boolean isBST) {
			this.ans = ans;
			this.isBST = isBST;
		}

	}

	public int solve(TreeNode A) {

		if (A == null)
			return 0;

		return findLargestBST(A).ans;

	}

	private Data findLargestBST(TreeNode root) {
		// when tree is null
		if (root == null) {
			return new Data(0, true);
		}
		// when only one node is present
		if (root.left == null && root.right == null) {
			return new Data(root.val, root.val, 1, 1, true);
		}
		Data ld = findLargestBST(root.left);
		Data rd = findLargestBST(root.right);
		int size = 1 + ld.size + rd.size;
		// if whole subtree is BST including root
		if (ld.isBST && rd.isBST && root.val > ld.max && root.val < rd.min) {
			return new Data(Math.min(ld.min, Math.min(root.val, rd.min)), Math.max(rd.max, Math.max(ld.max, root.val)),
					size, size, true);
		}
		// if whole is not subtree
		return new Data(Math.max(ld.ans, rd.ans), false);
	}

}
