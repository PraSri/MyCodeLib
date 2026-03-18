package BinarySearchTrees;

public class DistanceBetweenNodesOfBST {

	public static void main(String[] args) {

	}

	public int solve(TreeNode A, int B, int C) {

		// make sure B is smaller than C

		if (B > C) {
			int t = B;
			B = C;
			C = t;
		}

		return solveUtil(A, B, C);

	}

	private int solveUtil(TreeNode a, int b, int c) {
		if (a == null)
			return 0;
		int rootValue = a.val;
		if (rootValue > c && rootValue > b) {
			return solveUtil(a.left, b, c);
		}

		if (rootValue < c && rootValue < b) {
			return solveUtil(a.right, b, c);
		}

		return distFromRoot(a, b) + distFromRoot(a, c);
	}

	private int distFromRoot(TreeNode a, int b) {
		if (a.val == b) {
			return 0;
		}

		if (a.val > b) {
			return 1 + distFromRoot(a.left, b);
		}
		return 1 + distFromRoot(a.right, b);
	}

}
