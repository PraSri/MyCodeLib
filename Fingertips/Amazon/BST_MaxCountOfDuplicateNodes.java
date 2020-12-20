package Amazon;

import Trees.TreeNode;

public class BST_MaxCountOfDuplicateNodes {

	public static void main(String[] args) {
		TreeNode root = newNode(6);
		root.left = newNode(5);
		root.right = newNode(7);
		root.left.left = newNode(4);
		root.left.right = newNode(5);
		root.right.left = newNode(7);
		root.right.right = newNode(7);

		System.out.println("Node of BST is " + new BST_MaxCountOfDuplicateNodes().getMaxCountNode(root));
	}

	static TreeNode newNode(int data) {
		return new TreeNode(data);
	}

	// time complexity = O(N)
	public int getMaxCountNode(TreeNode root) {
		inorderTraversal(root);
		return count;
	}

	int max = -1;
	int count = 1;
	TreeNode prev = null;
	TreeNode curr = null;

	private void inorderTraversal(TreeNode root) {

		if (root == null) {
			return;
		}

		inorderTraversal(root.left);

		if (prev != null) {

			if (prev.val == curr.val) {
				count++;
			} else {
				count = 1;
			}

		}
		if (count > max) {
			max = count;
			curr = root;
		}
		prev = root;
		inorderTraversal(root.right);

	}

}
