package Misc;

import java.util.ArrayList;
import java.util.Arrays;

public class SwapElements {

	public static void main(String[] args) {

		int[] A = new int[] { 50, 12 };
		int[][] B = new int[][] { { 2, 1 },{1, 42},{1, 42} };
		for (int i : solve(A, B)) {
			System.out.print(i + " ");
		}

	}

	public static int[] solve(int[] A, int[][] B) {

		int n = A.length;

		ArrayList<Integer> a = new ArrayList<>();

		int max = Integer.MIN_VALUE;

		for (int i = 0; i < n; i++) {
			max = Math.max(max, A[i]);
		}

		for (int[] q : B) {
			if (q[1] == 0) {
				a.add(A[q[0] - 1]);
			} else if (q[1] > 1) {
				a.add(max);
			} else {
				int inx = q[0] - 1;
				int t = A[inx];
				for (int i = 1; i < inx; i++) {
					if (inx % i == 0)
						t = Math.max(t, A[i]);
				}
				int k = inx;
				inx++;
				while (k < n) {
					t = Math.max(t, A[k]);
					k = k + inx;
				}
				a.add(t);
			}
		}
		int j = 0;
		int[] b = new int[a.size()];
		for (int i : a) {
			b[j++] = i;
		}

		return b;
	}

}
