package Trees;

import java.util.ArrayList;

public class BoundaryTraversalOfBinaryTree {

	public static void main(String[] args) {
	}
	//https://www.geeksforgeeks.org/boundary-traversal-of-binary-tree/

	public ArrayList<Integer> solve(TreeNode A) {

		ArrayList<Integer> res = new ArrayList<Integer>();

		res.add(A.val);

		traverseLeftBoundary(A.left, res);

		traverseLeaves(A.left, res);
		traverseLeaves(A.right, res);

		traverseRightBoundary(A.right, res);

		return res;

	}

	private void traverseRightBoundary(TreeNode root, ArrayList<Integer> res) {
		if (root != null) {
			if (root.right != null) {
				traverseRightBoundary(root.right, res);
				res.add(root.val);
			} else if (root.left != null) {
				traverseRightBoundary(root.left, res);
				res.add(root.val);
			}
		}
	}

	private void traverseLeaves(TreeNode root, ArrayList<Integer> res) {

		if (root != null) {
			traverseLeaves(root.left, res);
			if (root.left == null && root.right == null)
				res.add(root.val);
			traverseLeaves(root.right, res);
		}

	}

	private void traverseLeftBoundary(TreeNode root, ArrayList<Integer> res) {

		if (root != null) {
			if (root.left != null) {
				res.add(root.val);
				traverseLeftBoundary(root.left, res);
			} else if (root.right != null) {
				res.add(root.val);
				traverseLeftBoundary(root.right, res);
			}
		}

	}

}
