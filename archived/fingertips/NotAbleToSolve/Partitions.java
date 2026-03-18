package NotAbleToSolve;

public class Partitions {

	public static void main(String[] args) {
		System.out.println(solve(4, new int[] { 0, 1, -1, 0 }));
	}

	/********
	 * 
	 * 
	 * You are given a 1D integer array B containing A integers.
	 * 
	 * Count the number of ways to split all the elements of the array into 3
	 * contiguous parts so that the sum of elements in each part is the same.
	 * 
	 * Such that : sum(B[1],..B[i]) = sum(B[i+1],...B[j]) = sum(B[j+1],...B[n])
	 * 
	 * 
	 ******/

	/********** HINT*- *************/

	/*
	 * For dividing array in 3 part we have a condition on the sum that it should be
	 * divisible by 3. Then think how can we access sum of ranges to check the
	 * condition for equal sum.
	 * 
	 **/

	/*******
	 * 
	 * Lets get basic case right first. Sum of the given array must be a multiple of
	 * 3 for any chance of it having 3 partitions. Next we create a suffix array,
	 * which will have a note of position where the sum is (sum/3) from the end.
	 * Therefore when we iterate from front, (keeping sum count) as soon as we reach
	 * (sum/3) let say at i, we have probably got our 1st part(still there can be a
	 * chance that 3 partitions won’t exist). Now as we are looking for 3 parts, the
	 * 3rd part will start from i+2 or thereafter (2nd part can be at i+1).
	 * Therefore from i+2 position we check on our suffix array for instances where
	 * we tagged positions as 1. In all the places we find that, we have a
	 * successful case of 3 partition and we increase a totalCount by 1. Then we
	 * resume from out i th index and do the same again when the sum is (sum/3).
	 * 
	 * 
	 *********/

	public static int solve(int A, int[] B) {

		int n = A;

		int sum = 0;

		for (int i : B) {
			sum += i;
		}

		if (sum % 3 != 0) {
			return 0;
		}

		int x = sum / 3;

		int[] t = new int[n];

		int ts = 0;
		for (int i = n - 1; i >= 0; i--) {

			ts += B[i];

			if (ts == x) {
				t[i] = 1;
			}

		}
		ts = 0;
		int total = 0;
		for (int i = 0; i < n; i++) {
			ts += B[i];
			if (ts == x) {
				for (int j = i + 2; j < n; j++) {

					if (t[j] == 1) {
						total++;
					}

				}
			}
		}

		return total;

	}
}
