package TreesNew;

public class BinaryTreeFromInorderAndPreorder {

	public static void main(String[] args) {

	}

	int pre = 0;

	public TreeNode buildTree(int[] A, int[] B) {

		int n = A.length;
		return buildUtil(A, B, 0, n - 1);

	}

	private TreeNode buildUtil(int[] a, int[] b, int s, int e) {

		if (s > e) {
			return null;
		}
		TreeNode root = new TreeNode(a[pre++]);

		if (s == e) {
			return root;
		}

		int index = getIndexInorder(root, b, s, e);

		if (index == -1) {
			return null;
		}

		root.left = buildUtil(a, b, s, index - 1);
		root.right = buildUtil(a, b, index + 1, e);

		return root;

	}

	private int getIndexInorder(TreeNode root, int[] b, int s, int e) {

		for (int i = s; i <= e; i++) {
			if (root.val == b[i]) {
				return i;
			}
		}

		return -1;
	}

}
