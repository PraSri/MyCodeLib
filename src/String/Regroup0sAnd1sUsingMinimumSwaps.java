package String;

public class Regroup0sAnd1sUsingMinimumSwaps {

	public static void main(String[] args) {

		System.out.println(solve("000111"));
		System.out.println(solve("1110101"));
		System.out.println(solve("11001101"));

	}

	public static int solve(final String A) {
		int n = A.length();
		int swaps1 = 0, swaps0 = 0, c1 = 0, c0 = 0;
		for (int i = 0; i < n; i++) {
			if (A.charAt(i) == '1') {
				c1++;
			} else {
				c0++;
			}
		}
		int i = n - 1;
		while (i >= 0) {

			if (A.charAt(i) == '1') {
				swaps1 += (i + 1) - c1;
				c1--;
			} else {
				swaps0 += (i + 1) - c0;
				c0--;
			}
			i--;

		}

		return Math.min(swaps0, swaps1);
	}

}
