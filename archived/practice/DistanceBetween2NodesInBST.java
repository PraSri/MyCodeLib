import BinarySearchTrees.TreeNode;

public class DistanceBetween2NodesInBST {

	public static void main(String[] args) {

	}

	/**
	 * Calculate distance between the 2 nodes of a Binary Search Tree
	 * 
	 * @param nodeA
	 * @param nodeB
	 * @return the distance
	 */
	static int getDistanceBetweenNodes(TreeNode root, TreeNode nodeA, TreeNode nodeB) {
		int distance = 0;
		// get LCA of node a & b // in BST LCA takes O(log n) time
		TreeNode lca = LCA(root, nodeA, nodeB);
		// answer is sum of distance of node a from LCA & distance of node b from LCA //
		// time O(log n)
		return (getDistanceFromLCA(lca, nodeA, distance) + getDistanceFromLCA(lca, nodeB, distance));
	}

	/**
	 * Calculate the distance between the node and its lowest common ancestor and
	 * 
	 * @param ancestor
	 * @param child
	 * @param distance
	 * @return distance(number of edges on the path between them)
	 */
	static int getDistanceFromLCA(TreeNode ancestor, TreeNode child, int distance) {
		if (ancestor == child) {
			return distance;
		}
		if ((child == ancestor.left) || (child == ancestor.right)) {
			distance += 1;
			return distance;
		}
		if (child.val < ancestor.val) {
			distance += 1;
			return getDistanceFromLCA(ancestor.left, child, distance);
		}
		distance += 1;
		return getDistanceFromLCA(ancestor.right, child, distance);
	}

	/**
	 * Find the lowest common ancestor of two nodes A and B given the root node
	 * 
	 * @param root
	 * @param nodeA
	 * @param nodeB
	 * @return lowest common ancestor
	 */
	static TreeNode LCA(TreeNode root, TreeNode nodeA, TreeNode nodeB) {
		if (root == null) {
			return null;
		}

		if (nodeA == root || nodeB == root) {
			return root;
		}

		TreeNode left = LCA(root.left, nodeA, nodeB);

		TreeNode right = LCA(root.right, nodeA, nodeB);

		if (left != null && right != null) {
			return root;
		}

		if (left == null) {
			return right;
		} else
			return left;
	}

	static TreeNode LCA_v2(TreeNode root, TreeNode nodeA, TreeNode nodeB) {
		if (root == null) {
			return null;
		}

		if (root.val > nodeA.val && root.val > nodeB.val) {
			return LCA(root.left, nodeA, nodeB);
		}

		if (root.val < nodeA.val && root.val < nodeB.val) {
			return LCA(root.right, nodeA, nodeB);
		}

		return root;
	}

}
