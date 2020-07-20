package Tree;

public class CheckIfGivenPreorderInorderPostorderTraversalsAreOfSameTree {

	public static void main(String[] args) {

		int[] A = new int[] { 1, 5, 4, 2, 3 };
		int[] B = new int[] { 4, 2, 5, 1, 3 };
		int[] C = new int[] { 4, 1, 2, 3, 5 };

		System.out.println(solve(A, B, C));

	}

	public static int solve(int[] A, int[] B, int[] C) {

		int n = A.length;

		return solveUtil(A, B, C, 0, n - 1);

	}

	private static int solveUtil(int[] pre, int[] in, int[] post, int s, int e) {
		if (s >= e) {
			return 1;
		}
		if (pre[s] != post[e]) {
			return 0;
		}

		int index = getIndex(in, pre[s]);

		if (index == -1) {
			return 0;
		}

		return solveUtil(pre, in, post, s + 1, e + index - pre.length);

	}

	private static int getIndex(int[] in, int val) {
		for (int i = 0; i < in.length; i++) {
			if (in[i] == val) {
				return i;
			}
		}
		return -1;
	}

}
