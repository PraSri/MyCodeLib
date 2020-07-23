package Trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class LeftViewOfBinaryTree {

	public static void main(String[] args) {

	}

	public ArrayList<Integer> solve(TreeNode A) {

		ArrayList<Integer> res = new ArrayList<Integer>();

		leftViewUtil(A, res);

		return res;

	}

	private void leftViewUtil(TreeNode a, ArrayList<Integer> res) {

		if (a == null) {
			return;
		}

		Queue<TreeNode> q = new LinkedList<TreeNode>();

		q.add(a);

		TreeNode end = null;

		while (!q.isEmpty()) {
			int size = q.size();
			int n = size;
			while (size > 0) {
				end = q.poll();
				if (size == n) {
					res.add(end.val);
				}
				if (end.left != null) {
					q.add(end.left);
				}
				if (end.right != null) {
					q.add(end.right);
				}
				size--;
			}

		}

	}

}
