package Backtracking;

public class SIXLETS {

	public static void main(String[] args) {

		int[] A = new int[] { 1, 2, 8 };
		int B = 2;

		System.out.println(solve(A, B));
	}

	/*
	 * Given a array of integers A of size N and an integer B.
	 * 
	 * Return number of non-empty subsequences of A of size B having sum <= 1000.
	 * 
	 * 
	 * >>>>>>>>>>>NEED TO UNDERSTAND SOLUTION<<<<<<<<<<<<<<<<<<<<
	 * 
	 * 
	 */

	static int ans = 0;

	public static int solve(int[] A, int B) {

		ans = 0;
		int index = 0, sum = 0, size = 0;
		recsol(A, B, index, sum, size);

		return ans;

	}

	private static void recsol(int[] a, int b, int index, int sum, int size) {
		if (sum > 1000) {
			return;
		}
		if (size == b) {
			if (sum <= 1000) {
				ans++;
			}
			return;
		}
		if (index == a.length) {
			return;
		}
		for (int i = index; i < a.length; i++) {
			recsol(a, b, i + 1, sum + a[i], size + 1);
		}
	}

}
