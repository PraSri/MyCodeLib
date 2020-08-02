package BinarySearchTrees;

import java.util.LinkedList;
import java.util.Queue;

public class SumBinaryTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int t2Sum(TreeNode A, int B) {

		return helper(A, B) ? 1 : 0;

	}

	private boolean helper(TreeNode root, int k) {

		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);
		while (!q.isEmpty()) {
			TreeNode t = q.poll();
			if (search(root, t, k - t.val))
				return true;
			if (t.left != null) {
				q.add(t.left);
			}
			if (t.right != null) {
				q.add(t.right);
			}
		}

		return false;
	}

	private boolean search(TreeNode root, TreeNode t, int k) {
		if (root == null)
			return false;
		return (root.val == k && root != t) || (root.val > k && search(root.left, t, k))
				|| (root.val < k && search(root.right, t, k));
	}

}
