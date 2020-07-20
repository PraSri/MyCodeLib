package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class TOPVIEW {

	public static void main(String[] args) {

	}

	public static class Pair {
		TreeNode node;
		int hd;

		public Pair(TreeNode node, int hd) {
			this.node = node;
			this.hd = hd;
		}

	}

	public ArrayList<Integer> solve(TreeNode A) {

		ArrayList<Integer> res = new ArrayList<>();

		if (A == null) {
			return res;
		}

		Map<Integer, TreeNode> m = new TreeMap<Integer, TreeNode>();

		Queue<Pair> q = new LinkedList<Pair>();

		Pair rootPair = new Pair(A, 0);

		q.add(rootPair);

		while (!q.isEmpty()) {

			Pair p = q.poll();

			int hd = p.hd;
			TreeNode node = p.node;

			if (!m.containsKey(hd)) {
				m.put(hd, node);
			}

			if (node.left != null) {
				q.add(new Pair(node.left, hd - 1));
			}
			if (node.right != null) {
				q.add(new Pair(node.right, hd + 1));
			}

		}
		for (Map.Entry<Integer, TreeNode> me : m.entrySet()) {

			res.add(me.getValue().val);

		}

		return res;

	}

}
