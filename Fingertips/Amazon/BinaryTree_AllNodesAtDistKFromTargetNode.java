package Amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import Trees.InorderTraversal;
import Trees.TreeNode;
import helper.MyPrint;
import helperimpl.MyPrintIntegerArray;

public class BinaryTree_AllNodesAtDistKFromTargetNode {

	public static void main(String[] args) {

	}

	/*****
	 * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
	 **********/
	/*********
	 * We are given a binary tree (with root node root), a target node, and an
	 * integer value K.
	 * 
	 * Return a list of the values of all nodes that have a distance K from the
	 * target node. The answer can be returned in any order.
	 * 
	 **********************/

	Map<TreeNode, List<TreeNode>> cp;

	public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {

		// buildChildParentMap
		cp = new HashMap<TreeNode, List<TreeNode>>();
		buildMap(root, null);
		// now this tree can act as graph and target node as source
		// BFS and count level till level=k

		return bfs(target, k);

	}

	private List<Integer> bfs(TreeNode src, int k) {
		List<Integer> res = new ArrayList<Integer>();
		if (!cp.containsKey(src))
			return res;
		Set<TreeNode> visited = new HashSet<TreeNode>();
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(src);
		visited.add(src);
		while (!queue.isEmpty()) {

			int size = queue.size();
			if (k == 0) {
				for (int i = 0; i < size; i++) {
					res.add(queue.poll().val);
					return res;
				}
			}

			for (int i = 0; i < size; i++) {
				TreeNode curr = queue.poll();
				for (TreeNode neigh : cp.get(curr)) {
					if (visited.contains(neigh))
						continue;
					visited.add(neigh);
					queue.add(neigh);
				}
			}

			k--;

		}
		return res;
	}

	private void buildMap(TreeNode child, TreeNode parent) {
		if (child == null) {
			return;
		}
		if (!cp.containsKey(child)) {
			cp.put(child, new ArrayList<TreeNode>());
			if (parent != null) {
				cp.get(child).add(parent);
				cp.get(parent).add(child);
			}
			buildMap(child.left, child);
			buildMap(child.right, child);
		}

	}

	class LeetcodeSol {
		Map<TreeNode, List<TreeNode>> map = new HashMap();
		// here can also use Map<TreeNode, TreeNode> to only store the child - parent
		// mapping, since parent-child mapping is inherent in the tree structure

		public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
			List<Integer> res = new ArrayList<Integer>();
			if (root == null || K < 0)
				return res;
			buildMap(root, null);
			if (!map.containsKey(target))
				return res;
			Set<TreeNode> visited = new HashSet<TreeNode>();
			Queue<TreeNode> q = new LinkedList<TreeNode>();
			q.add(target);
			visited.add(target);
			while (!q.isEmpty()) {
				int size = q.size();
				if (K == 0) {
					for (int i = 0; i < size; i++)
						res.add(q.poll().val);
					return res;
				}
				for (int i = 0; i < size; i++) {
					TreeNode node = q.poll();
					for (TreeNode next : map.get(node)) {
						if (visited.contains(next))
							continue;
						visited.add(next);
						q.add(next);
					}
				}
				K--;
			}
			return res;
		}

		private void buildMap(TreeNode node, TreeNode parent) {
			if (node == null)
				return;
			if (!map.containsKey(node)) {
				map.put(node, new ArrayList<TreeNode>());
				if (parent != null) {
					map.get(node).add(parent);
					map.get(parent).add(node);
				}
				buildMap(node.left, node);
				buildMap(node.right, node);
			}
		}
	}
}
