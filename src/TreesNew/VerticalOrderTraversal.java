package Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class VerticalOrderTraversal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static class Pair {
		TreeNode node;
		int hd;

		public Pair(TreeNode node, int hd) {
			this.node = node;
			this.hd = hd;
		}

	}

	public ArrayList<ArrayList<Integer>> verticalOrderTraversal(TreeNode A) {

		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();

		if (A == null) {
			return res;
		}

		Map<Integer, List<TreeNode>> m = new TreeMap<Integer, List<TreeNode>>();

		Queue<Pair> q = new LinkedList<Pair>();

		Pair rootPair = new Pair(A, 0);

		q.add(rootPair);

		while (!q.isEmpty()) {

			Pair p = q.poll();

			int hd = p.hd;
			TreeNode node = p.node;

			if (!m.containsKey(hd)) {
				List<TreeNode> l = new ArrayList<TreeNode>();
				l.add(node);
				m.put(hd, l);
			} else {
				m.get(hd).add(node);
			}

			if (node.left != null) {
				q.add(new Pair(node.left, hd - 1));
			}
			if (node.right != null) {
				q.add(new Pair(node.right, hd + 1));
			}

		}
		for (Map.Entry<Integer, List<TreeNode>> me : m.entrySet()) {

			ArrayList<Integer> l = new ArrayList<Integer>();

			for (TreeNode t : me.getValue()) {
				l.add(t.val);
			}

			res.add(l);

		}

		return res;

	}
}
