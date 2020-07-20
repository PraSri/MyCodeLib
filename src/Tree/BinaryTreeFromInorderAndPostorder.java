package Tree;

public class BinaryTreeFromInorderAndPostorder {

	public static void main(String[] args) {
	}

	public TreeNode buildTree(int[] A, int[] B) {
		int n = A.length;
		return buildTreeUtil(A, B, 0, n - 1, n - 1);
	}

	private TreeNode buildTreeUtil(int[] in, int[] post, int s, int e, int pos) {

		if (s > e)
			return null;

		TreeNode root = new TreeNode(post[pos]);

		int ind = getIndex(in, s, e, post[pos]);

		if (ind == -1) {
			return null;
		}

		root.left = buildTreeUtil(in, post, s, ind - 1, pos - (e - ind + 1));
		root.right = buildTreeUtil(in, post, ind + 1, e, pos - 1);

		return root;
	}

	private int getIndex(int[] in, int s, int e, int rootValue) {
		for (int i = s; i <= e; i++) {
			if (in[i] == rootValue) {
				return i;
			}
		}
		return -1;
	}

}
