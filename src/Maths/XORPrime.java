package Maths;

import java.util.ArrayList;
import java.util.Arrays;

public class XORPrime {

	public static void main(String[] args) {
		int mod = 1000000007;
//		System.out.println(mod);
		System.out.println(solve(new int[] { 10, 45 }));

	}

	/*
	 * Question : Given an array A of integers of size N. For each integer in A,
	 * there is a set S of the prime factors of that integer.
	 * 
	 * There is a good value associated with each number, which is the sum of XOR of
	 * all subsets of set S modulo 109+7.
	 * 
	 * Good Value of the array A is the sum of good values associated with each
	 * number modulo 109+7.
	 * 
	 * Return Good Value of A.
	 * 
	 * Note: Set S has no duplicates.
	 * 
	 * 
	 * 
	 */

	/*
	 * This solution can be referenced to get idea of getting prime using sieve and
	 * sum of XOR of subsets of a given set
	 * 
	 * 
	 */

	public static int solve(int[] A) {

		int n = A.length;

		boolean[] prime = new boolean[100005];

		Arrays.fill(prime, true);

		ArrayList<Integer> primes = new ArrayList<>();

		for (int i = 2; i * i <= 100000; i++) {
			if (prime[i]) {
				primes.add(i);
				for (int j = i * i; j <= 100000; j += i) {
					prime[j] = false;
				}
			}
		}

		int res = 0;
		int mod = 1000000007;

		for (int i = 0; i < n; i++) {
			int x = A[i];
			ArrayList<Integer> pfac = new ArrayList<>();
			int j = 0;
			while (j < primes.size()) {
				if (x % primes.get(j) == 0) {
					pfac.add(primes.get(j));
					while (x % primes.get(j) == 0) {
						x = x / primes.get(j);
					}
					if (x == 1) {
						break;
					}
				}
				j++;
			}
			if (x != 1) {
				pfac.add(x);
			}

			int gv = 0;
			int nof = pfac.size();
			int nos = 1 << nof;
			for (int k = 0; k < nos; k++) {
				int t = 0;
				for (int l = 0; l < nof; l++) {
					int flag = k & (1 << l);
					if (flag > 0) {
						t = t ^ pfac.get(l);
					}
				}
				gv = (gv + t) % mod;
			}
			res = res + (gv) % mod;
		}

		return res;

	}

}
