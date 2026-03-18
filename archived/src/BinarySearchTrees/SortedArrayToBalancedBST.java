package BinarySearchTrees;

public class SortedArrayToBalancedBST {

	public static void main(String[] args) {

		SortedArrayToBalancedBST test = new SortedArrayToBalancedBST();

		int[] A = new int[] { 1, 2, 3, 5, 10, 11 };

		TreeNode BSTroot = test.sortedArrayToBST(A);

		boolean isBalanced = BalancedBinaryTree.isBalanced(BSTroot) == 1 ? true : false;

		System.out.println("isBalanced : " + isBalanced);

		boolean isValidBST = new ValidBinarySearchTree().isValidBST(BSTroot) == 1 ? true : false;

		System.out.println("isValidBST : " + isValidBST);

	}

	public TreeNode sortedArrayToBST(final int[] A) {
		int n = A.length;
		return helper(A, 0, n - 1);
	}

	private TreeNode helper(int[] a, int s, int e) {
		if (s > e) {
			return null;
		}

		int mid = (s + e) / 2;

		TreeNode root = new TreeNode(a[mid]);

		root.left = helper(a, s, mid - 1);
		root.right = helper(a, mid + 1, e);

		return root;

	}

	public static class BalancedBinaryTree

	{

		public static int isBalanced(TreeNode A) {

			if (heightOfTree(A) != -1) {
				return 1;
			}

			return 0;

		}

		public static int isBal = 1;

		private static int heightOfTree(TreeNode a) {

			if (a == null)
				return 0;

			int hl = heightOfTree(a.left);
			int hr = heightOfTree(a.right);

			if (hl == -1 || hr == -1 || Math.abs(hl - hr) > 1) {
				isBal = 0;
				/**
				 * A height-balanced binary tree is defined as a binary tree in which the depth
				 * of the two subtrees of every node never differ by more than 1.
				 * 
				 * 
				 */
				return -1;
			}

			return Math.max(hl, hr) + 1;

		}
	}
}
