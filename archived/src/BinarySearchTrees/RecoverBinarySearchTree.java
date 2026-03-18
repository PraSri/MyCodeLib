package BinarySearchTrees;

public class RecoverBinarySearchTree {

	public static void main(String[] args) {
	}

	TreeNode first = null;
	TreeNode second = null;
	TreeNode pre = new TreeNode(Integer.MIN_VALUE);

	public int[] recoverTree(TreeNode A) {

		int[] ans = new int[2];

		inOrderTraversal(A);

		ans[0] = first.val;
		ans[1] = second.val;

		return ans;

	}

	private void inOrderTraversal(TreeNode a) {
//6, 3, 4, 5, 2
		if (a == null)
			return;
		inOrderTraversal(a.left);

		if (first == null && pre.val >= a.val) {
			first = pre;
		}

		if (first != null && pre.val >= a.val) {
			second = a;
		}

		pre = a;

		inOrderTraversal(a.right);

	}

}
