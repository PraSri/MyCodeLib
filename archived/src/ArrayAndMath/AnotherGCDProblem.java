package ArrayAndMath;

import java.util.ArrayList;
import java.util.Arrays;

public class AnotherGCDProblem {

	/**
	 * Given an integer array A of size N. Find the maximum length of a subarray Al,
	 * Al+1 ... Ar such that gcd(A[l], A[l+1], ... A[r]) > 1.
	 * 
	 * NOTE: If no such subarray exists, return -1.
	 * 
	 */

	public static void main(String[] args) {

		System.out.println(solve_v2(new int[] { 98123, 98123, 98123 }));
	}

	public int solve(int[] A) {

		int n = A.length;

//		int mv = Arrays.stream(A).max().getAsInt();

		int s = 2, e = n;
		;
		int ans = -1;
		while (s <= e) {
			int mid = (s + e) / 2;
			if (isExist(A, mid)) {
				ans = mid;
				s = mid + 1;
			} else {
				e = mid - 1;
			}
		}

		return ans;

	}

	private boolean isExist(int[] a, int mid) {

		int g = 0;
		for (int i = 0; i < mid; i++) {
			for (int j = i; j < mid; j++) {
				g = gcd(g, a[j]);
			}
			if (g > 1) {
				return true;
			}
		}

		return false;
	}

	private int gcd(int a, int b) {
		if (b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}

	public static int solve_v2(int[] A) {

		int n = A.length;
		ArrayList<Integer> primes = getPrimes();
		ArrayList[] l = new ArrayList[1000006];
		for (int i = 0; i < 1000006; i++) {
			l[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < n; i++) {
			for (int p : primes) {
				if (A[i] % p == 0) {
					l[p].add(i);
				}
			}
		}
		int max = 0;
		for (int i = 0; i < l.length; i++) {
			if (l[i].size() > 0) {
				max = Math.max(max, getLongestConsecutiveNumbersLength(l[i]));
			}
		}

		return max == 0 ? -1 : max;

	}

	private static int getLongestConsecutiveNumbersLength(ArrayList<Integer> a) {
		int n = a.size();

		int i = 1;
		int l = 1;
		int max = 1;

		while (i < n) {
			if (a.get(i) == a.get(i - 1) + 1) {
				l++;
				max = Math.max(l, max);
			} else {
				l = 1;
			}
			i++;
		}

		return max;

	}

	private static ArrayList<Integer> getPrimes() {

		boolean[] prime = new boolean[1000007];

		Arrays.fill(prime, true);

		ArrayList<Integer> primes = new ArrayList<>();

		for (int i = 2; i * i <= 1000006; i++) {
			if (i == 98123) {
				System.out.println("HELLO");
			}
			if (prime[i]) {
//				primes.add(i);
				for (int j = i * i; j <= 1000006; j += 2 * i) {
					prime[j] = false;
				}
			}
		}

		for (int i = 2; i < 1000007; i++) {
			if (prime[i]) {
				primes.add(i);
			}
		}
//		System.out.println(primes.contains(98123) + " " + prime[98123]);
		return primes;
	}

}
