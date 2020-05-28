package Sorting;

import java.util.Arrays;
import java.util.HashMap;

public class ArrayWithConsecutiveElements {

	public static void main(String[] args) {

		System.out.println(solve_v2(new int[] { 23, 176, 200, 187, 173, 170, 110, 120, 88, 79, 77, 26, 193, 80, 192,
				134, 171, 183, 59, 92, 182, 94, 181, 156, 163, 185, 155, 78, 131, 154, 184, 186, 121, 122, 106, 108,
				201, 81, 130, 70, 62, 42, 172, 132, 105, 56, 93, 157, 133, 64, 50, 95, 177, 119, 24, 127, 32, 117, 76,
				25, 82, 167, 123, 190, 31, 164, 29, 71, 90, 100, 116, 89, 189, 124, 178, 47, 15, 137, 202, 96, 33, 174,
				204, 54, 165, 44, 199, 16, 109, 159, 125, 68, 158, 160, 195, 74, 102, 83, 126, 97, 61, 91, 111, 147,
				161, 60, 17, 135, 151, 72, 148, 188, 149, 152, 53, 58, 128, 129, 136, 138, 175, 191, 98, 67, 55, 162,
				49, 168, 166, 169, 194, 43, 179, 180, 139, 140, 107, 45, 196, 101, 150, 118, 34, 9, 197, 113, 11, 141,
				27, 198, 73, 10, 142, 63, 203, 12, 143, 13, 99, 84, 48, 40, 14, 144, 69, 22, 18, 103, 19, 57, 35, 20,
				21, 51, 46, 52, 65, 85, 145, 28, 30, 66, 39, 36, 37, 146, 75, 86, 153, 112, 38, 104, 41, 87, 114,
				115 }));

		System.out.println("&&&&&&&&&&&&&&&&&&");

		System.out.println(solve_v2(new int[] { 1, 2, 3, 4, 5 }));

		System.out.println("*****************");
		System.out.println(solve_v2(new int[] { 1, 1, 1, 5, 5 }));
	}

	public static int solve(int[] A) {
		int n = A.length;
		Arrays.sort(A);
		double d = ((double) n) / 2;
//		System.out.println("d" + d);
		double expSum = d * (2 * A[0] + n - 1);
		int actSum = 0;
		for (int i = 0; i < n; i++) {

			actSum += A[i];

		}

		System.out.println("expSum : " + expSum);
		System.out.println("actSum : " + actSum);

		return (int) expSum == actSum ? 1 : 0;
	}

	public static int solve_v2(int[] A) {

		int min = Integer.MAX_VALUE;
		int n = A.length;
		HashMap<Integer, Integer> hm = new HashMap<>();
		for (int i = 0; i < n; i++) {
			min = Math.min(A[i], min);

			if (!hm.containsKey(A[i])) {
				hm.put(A[i], 1);
			} else {
				return 0;
			}

		}
		int t = 0;
		for (int i = min; i < min + n; i++) {
//			System.out.println("i : " + i);
			t = t ^ i;
		}

//		System.out.println("T:" + t);

		int x = 0;

		for (int i = 0; i < n; i++) {
//			System.out.println("A[i]: " + A[i]);
			x = x ^ A[i];
		}

//		System.out.println("x:" + x);

		return (x ^ t) == 0 ? 1 : 0;

	}

}
