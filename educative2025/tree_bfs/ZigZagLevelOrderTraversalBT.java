package tree_bfs;

import Trees.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ZigZagLevelOrderTraversalBT {

	public static void main(String[] args) {
	}

	public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode A) {

		ArrayList<ArrayList<Integer>> res = new ArrayList<>();

		travel(A, res);

		return res;
	}

	private void travel(TreeNode a, ArrayList<ArrayList<Integer>> res) {

		Queue<TreeNode> q = new LinkedList<>();

		boolean reverse = false;

		q.add(a);

		while (!q.isEmpty()) {

			int size = q.size();

			ArrayList<Integer> l = new ArrayList<>();

			for (int i = 0; i < size; i++) {
				TreeNode t = q.poll();

				if (reverse) {

					l.add(0, t.val);

				} else {
					l.add(t.val);
				}

				if (t.left != null) {
					q.add(t.left);
				}
				if (t.right != null) {
					q.add(t.right);
				}

			}

			res.add(l);
			reverse = !reverse;

		}

	}

}
