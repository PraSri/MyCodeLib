package Sorting;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GameOfBottles {

	public static void main(String[] args) {

		int[] A = new int[] { 1, 2, 3, 3,3, 2 };

		System.out.println(solve(A));

	}

	public static int solve(int[] A) {

		Arrays.sort(A);

		int n = A.length;

		Map<Integer, Integer> m = new HashMap<>();

		for (int i = 0; i < n; i++) {
			if (!m.containsKey(A[i])) {
				m.put(A[i], 1);
			} else {
				m.put(A[i], m.get(A[i]) + 1);
			}
		}
		int ct = 0;
		int maxDup = 0;
		for (Map.Entry<Integer, Integer> mp : m.entrySet()) {
			if (mp.getValue() > maxDup) {
				maxDup = mp.getValue();
			}
		}

		ct = maxDup;

		return ct;

	}

}
