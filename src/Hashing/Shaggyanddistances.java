package Hashing;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Shaggyanddistances {

	public static void main(String[] args) {
		System.out.println(solve(new int[] { 7, 1, 3, 4, 1, 7 }));
	}

	public static class Pair {
		int diffOfIndex;
		int frequency;

		Pair(int diffOfIndex, int frequency) {
			this.diffOfIndex = diffOfIndex;
			this.frequency = frequency;
		}
	}

	public static int solve(int[] A) {

		Map<Integer, Pair> m = new HashMap<>();

		// key = A[i] value = diff

		int n = A.length;
		int minv = Integer.MAX_VALUE;
		int diff;
		for (int i = 0; i < n; i++) {

			if (!m.containsKey(A[i])) {
				m.put(A[i], new Pair(i, 1));
			} else {
				m.put(A[i], new Pair(Math.abs(m.get(A[i]).diffOfIndex - i), m.get(A[i]).frequency + 1));
			}

		}

		for (Entry<Integer, Pair> e : m.entrySet()) {
			Pair v = e.getValue();
			if (v.frequency == 2) {
				if (v.diffOfIndex < minv) {
					minv = v.diffOfIndex;
				}
			}
		}

		return minv;

	}

}
