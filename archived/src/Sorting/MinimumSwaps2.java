package Sorting;

public class MinimumSwaps2 {

	public static void main(String[] args) {
		int[] A = new int[] { 1, 2, 3, 4, 0 };
		System.out.println(solve(A));
	}

	public static int solve(int[] A) {

		int n = A.length;
		int i = 0;
		int c = 0;
		while (i < n) {
			if (A[i] != i) {
				while (A[i] != i) {
					int t = 0;
					t = A[A[i]];
					A[A[i]] = A[i];
					A[i] = t;
					c++;
				}
			}
			i++;
		}

		return c;

	}

}
