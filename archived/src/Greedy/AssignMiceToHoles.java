package Greedy;

import java.util.Arrays;

public class AssignMiceToHoles {

	public static void main(String[] args) {
	}

	public int mice(int[] A, int[] B) {

		Arrays.sort(A);
		Arrays.sort(B);

		int ans = Integer.MIN_VALUE;

		for (int i = 0; i < A.length; i++) {
			if (Math.abs(A[i] - B[i]) > ans) {
				ans = Math.max(Math.abs(A[i] - B[i]), ans);
			}
		}

		return ans;
	}

}
