package FirstAttemptSolved;

import QuickHelper.ModularPowerInLogN;
import QuickHelper.Modulo_1000000007;
import QuickHelper.PrimeNumbers;

public class PrimeSubsequences {

	public static void main(String[] args) {
		System.out.println(solve(new int[] { 1, 2, 3, 4, 5 }));
	}

	/*****
	 * Given an array A having N positive numbers. You have to find the number of
	 * Prime subsequences of A.
	 * 
	 * A Prime subsequence is one that has only prime numbers, for example [2, 3],
	 * [5] are the Prime subsequences where [2, 4] and [1, 2, 3, 4] are not.
	 * 
	 * 
	 *******/
	public static int solve(int[] A) {

		boolean[] isPrime = PrimeNumbers.sievePrime();

		int primeCt = 0;

		for (int i : A) {
			if (isPrime[i]) {
				primeCt++;
			}
		}

		System.out.println("primeCt : " + primeCt);

		int twoPowerN = ModularPowerInLogN.fastPower(2, primeCt, Modulo_1000000007.mod);

		return twoPowerN - 1;

	}
}
