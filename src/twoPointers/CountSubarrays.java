package twoPointers;

public class CountSubarrays {

	public static void main(String[] args) {
		int[] A = new int[] { 93, 9, 12, 32, 97, 75, 32, 77, 40, 79, 61, 42, 57, 19, 64, 16, 86, 47, 41, 67, 76, 63, 24,
				10, 25, 96, 1, 30, 73, 91, 70, 65, 53, 75, 5, 19, 65, 6, 96, 33, 73, 55, 4, 90, 72, 83, 54, 78, 67, 56,
				8, 70, 43, 63 };
		System.out.println(solve(A));
	}

	/*
	 * 
	 * 1,1,3 Misha likes finding all Subarrays of an Array. Now she gives you an
	 * array A of N elements and told you to find the number of subarrays of A, that
	 * have unique elements.
	 * 
	 * Since the number of subarrays could be large, return value % 109 +7.
	 */

	public static int mod = 1000000007;

	public static int solve(int[] A) {
		int n = A.length;
		int[] f = new int[1000001];
		int s = 0;
		int e = 0;// 1 1 3
		long c = 0;
		while (e < n) {
			if (f[A[e]] == 0) {
				f[A[e]]++;
				e++;
			} else {
				long x = e - s;
				long l = (x * (x + 1)) / 2;
				c = (c % mod + l % mod) % mod;
				while (s < e && A[s] != A[e]) {
					f[A[s]]--;
					s++;
				}
				s++;
				x = e - s;
				l = (x * (x + 1)) / 2;
				c = (c - l + mod) % mod;
				e++;
			}
//			while (e < n && f[A[e]] == 0) {
//				System.out.print(A[e] + " ");
//				f[A[e]]++;
//				e++;
//			}
//			int x = (e - s) * (e - s + 1) / 2;
//			c = c + x;
//			while (e < n && f[A[e]] > 0) {
//				f[A[s]] = 0;
//				s++;
//			}
//			s++;
//			x = (e - s) * (e - s + 1) / 2;
//			c = c - x;
//			e++;
//			System.out.println();
		}
		long y = e - s;
		long m = (y * (y + 1)) / 2;
		c = (c % mod + m % mod) % mod;
		return (int) c;
	}

}
