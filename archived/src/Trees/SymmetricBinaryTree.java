package Trees;

public class SymmetricBinaryTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int isSymmetric(TreeNode A) {

		if (A == null) {
			return 1;
		}

		return isSym(A.left, A.right) ? 1 : 0;

	}

	private boolean isSym(TreeNode a, TreeNode b) {
		if (a == null && b == null)
			return true;

		if (a == null || b == null)
			return false;

		if (a.val != b.val)
			return false;

		return isSym(a.left, b.right) && isSym(a.right, b.left);
	}

}
