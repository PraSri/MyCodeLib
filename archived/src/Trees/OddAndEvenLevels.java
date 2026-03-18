package Trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class OddAndEvenLevels {

	public static void main(String[] args) {
	}
	
	// Given a binary tree of integers. Find the difference between the sum of nodes at odd level and sum of nodes at even level.

	public int solve(TreeNode A) {
		return travel(A);
	}

	private int travel(TreeNode a) {

		Queue<TreeNode> q = new LinkedList<TreeNode>();

		boolean reverse = false;

		q.add(a);
		int es = 0, os = 0;

		while (!q.isEmpty()) {

			int size = q.size();

			for (int i = 0; i < size; i++) {
				TreeNode t = q.poll();

				if (reverse) {
					es += t.val;

				} else {
					os += t.val;
				}

				if (t.left != null) {
					q.add(t.left);
				}
				if (t.right != null) {
					q.add(t.right);
				}

			}

			reverse = !reverse;

		}

		return os - es;

	}

}
