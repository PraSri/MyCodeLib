package tree_dfs;

import BinarySearchTrees.TreeNode;

public class ValidBinarySearchTree {

	public static void main(String[] args) {

	}

	public int isValidBST(BinarySearchTrees.TreeNode A) {
		if (A == null)
			return 1;
		return isValidBSTBoolean(A, Integer.MIN_VALUE, Integer.MAX_VALUE) ? 1 : 0;

	}

	private boolean isValidBSTBoolean(TreeNode a, int minValue, int maxValue) {

		if (a == null) {
			return true;
		}

		if (a.val >= maxValue || a.val <= minValue)
			return false;

		return isValidBSTBoolean(a.left, minValue, a.val) && isValidBSTBoolean(a.right, a.val, maxValue);

	}

}
