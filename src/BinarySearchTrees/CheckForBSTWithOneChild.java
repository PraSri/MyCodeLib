package BinarySearchTrees;

public class CheckForBSTWithOneChild {

	public static void main(String[] args) {

	}

	public String solve(int[] A) {

		int n = A.length;

		int min, max;

		if (A[n - 1] > A[n - 2]) {
			max = A[n - 1];
			min = A[n - 2];
		} else {
			max = A[n - 2];
			min = A[n - 1];
		}

		for (int i = n - 3; i >= 0; i--) {

			if (A[i] > max) {
				max = A[i];
			} else if (A[i] < min) {
				min = A[i];
			} else {
				return "NO";
			}
		}

		return "YES";

	}

}
