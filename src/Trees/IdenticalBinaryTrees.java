package Trees;

public class IdenticalBinaryTrees {

	public static void main(String[] args) {

	}

	public boolean isSameTreeUtil(TreeNode A, TreeNode B) {

		if (A == null && B == null) {
			return true;
		}

		if (A != null && B != null)

			return (A.val == B.val) && isSameTreeUtil(A.left, B.left) && isSameTreeUtil(A.right, B.right);

		return false;

	}

}
