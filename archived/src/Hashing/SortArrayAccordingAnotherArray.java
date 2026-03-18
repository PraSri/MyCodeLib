package Hashing;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class SortArrayAccordingAnotherArray {

	public static void main(String[] args) {
		for (int x : solve(new int[] { 5, 17, 100, 11 }, new int[] { 1, 100 })) {
			System.out.print(x + " ");
		}
		// 5, 4, 2, 1, 3
		// 100, 5, 11, 17
	}

	public static int[] solve(int[] A, int[] B) {

		int n = A.length;

		Map<Integer, Integer> m = new TreeMap<>();

		for (int i = 0; i < n; i++) {
			if (!m.containsKey(A[i])) {
				m.put(A[i], 1);
			} else {
				m.put(A[i], m.get(A[i]) + 1);
			}
		}

//		for (Entry<Integer, Integer> e : m.entrySet()) {
//			System.out.println(e.getKey() + " " + e.getValue());
//		}

		int l = B.length;
		int k = 0;
		for (int i = 0; i < l; i++) {
			if (m.containsKey(B[i])) {
				int x = m.get(B[i]);
				while (x > 0) {
					A[k] = B[i];
					k++;
					x--;
				}
				m.remove(B[i]);
			}
		}

//		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");

//		for (Entry<Integer, Integer> e : m.entrySet()) {
//			System.out.println(e.getKey() + " " + e.getValue());
//		}

		for (Entry<Integer, Integer> e : m.entrySet()) {
			int ky = e.getKey();
			int v = e.getValue();
			while (v > 0) {
				A[k] = ky;
				k++;
				v--;
			}

		}
		return A;

	}

}
