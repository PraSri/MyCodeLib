package Easy;

import java.util.Arrays;

public class SmallestNumberByRearrangingDigits {

	public static void main(String[] args) {

		System.out.println(getSmallestNumberRearrangedForSmallerRanger(846903));
		System.out.println(getSmallestPermutationForLargeNumber(5468001));

	}

	/***
	 * Given a long integer, return the smallest(magnitude) integer permutation of
	 * that number.
	 * 
	 * OR
	 * 
	 * Find smallest permutation of given number
	 **************/

	public static int getSmallestNumberRearrangedForSmallerRanger(int n) {

		int[] f = new int[10];

		int x = n;

		while (x > 0) {
			int r = x % 10;
			f[r]++;
			x = x / 10;
		}

		int result = 0;

		for (int i = 1; i < 10; i++) {
			if (f[i] > 0) {
				f[i]--;
				result = i;
				break;
			}
		}

		for (int i = 0; i < 10; i++) {
			while (f[i] > 0) {
				result = 10 * result + i;
				f[i]--;
			}
		}

		return result;

	}

	public static String getSmallestPermutationForLargeNumber(long n) {

		String s = String.valueOf(n);

		char[] c = s.toCharArray();

		return getSmallestPermutation(c);
	}

	private static String getSmallestPermutation(char[] c) {

		// this solution can more be optimized by writing our custom sort method running
		// in linear time . i.e. , counting sort
		Arrays.sort(c);

		int i = 0;
		while (c[i] == '0') {
			i++;
		}

		char t = c[0];
		c[0] = c[i];
		c[i] = t;

		return String.valueOf(c);
	}

}
