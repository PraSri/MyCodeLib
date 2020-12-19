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
		
		TreeNode root = createTreeFromArray(new Integer[] {3,5,1,6,2,0,8,null,null,7,4});
		int[] ans = InorderTraversal.inorderTraversal(root);
		MyPrintIntegerArray.printArray(ans);

	}
	
	public static TreeNode createTreeFromArray(Integer[] treeArray) {
		
		if(treeArray.length==0 || treeArray[0]==null) {
			return null;
		}
		return build(treeArray,0);
		
	}

	private static TreeNode build(Integer[] a,int i) {
		// a[0] = root
		// a[i] = parent
		// 2*i = left child
		// 2*i+1 = right child
		
		if(a[i]==null || i==a.length) {
			return null;
		}
		
		TreeNode root = new TreeNode(a[i]);
		root.left = build(a, i+1);
		root.right = build(a, i+2);
		return root;
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
		if(!cp.containsKey(src))
			return res;
		Set<TreeNode> visited = new HashSet<TreeNode>();
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(src);
		visited.add(src);
		while(!queue.isEmpty()) {
			
			int size = queue.size();
			if(k==0) {
				for(int  i =0;i<size;i++) {
					res.add(queue.poll().val);
					return res;
				}
			}
			
			for(int i = 0;i<size;i++) {
				TreeNode curr = queue.poll();
				for(TreeNode neigh : cp.get(curr)) {
					if(visited.contains(neigh))
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
		}
		if(parent!=null) {
			cp.get(child).add(parent);
			cp.get(parent).add(child);
			buildMap(child.left, child);
			buildMap(child.right, child);
		}
	}

}
