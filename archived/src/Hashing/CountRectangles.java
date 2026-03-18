package Hashing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CountRectangles {

	public static void main(String[] args) {

		int[] A = new int[] { 1, 1, 2, 2, 3, 3 };
		int[] B = new int[] { 1, 2, 1, 2, 1, 2 };
		System.out.println(solve(A, B));

	}

	public static int solve(int[] A, int[] B) {

		Map<Integer, Set<Integer>> m = new HashMap<>();
		Set<Integer> h;

		int n = A.length;

		for (int i = 0; i < n; i++) {
			if (!m.containsKey(A[i])) {
				h = new HashSet<>();
			} else {
				h = m.get(A[i]);
			}
			h.add(B[i]);
			m.put(A[i], h);
		}

		int diagonals = 0;

		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (A[i] == A[j] || B[i] == B[j]) {
					continue;
				}

				if (m.get(A[i]).contains(B[j]) && m.get(A[j]).contains(B[i])) {
					diagonals++;
				}
			}
		}

		int ans = diagonals >> 1;
		return ans;
	}

}
