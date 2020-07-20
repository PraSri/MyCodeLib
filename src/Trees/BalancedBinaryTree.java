package Trees;

public class BalancedBinaryTree {

	public static void main(String[] args) {

	}

	public int isBalanced(TreeNode A) {

		if (heightOfTree(A) != -1) {
			return 1;
		}

		return 0;

	}

	public static int isBal = 1;

	private int heightOfTree(TreeNode a) {

		if (a == null)
			return 0;

		int hl = heightOfTree(a.left);
		int hr = heightOfTree(a.right);

		if (hl == -1 || hr == -1 || Math.abs(hl - hr) > 1) {
			isBal = 0;
			/**
			 * A height-balanced binary tree is defined as a binary tree in which the depth
			 * of the two subtrees of every node never differ by more than 1.
			 * 
			 * 
			 */
			return -1;
		}

		return Math.max(hl, hr) + 1;

	}

}
