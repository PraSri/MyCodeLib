package Sorting;

import java.util.Arrays;

public class SumtheDifference {

	public static void main(String[] args) {
		System.out.println(solve1(new int[] { 5, 4, 2 }));
//		System.out.println(binarySearch(5, new int[] { 2,4,5 }));
//		System.out.println(fastPower(2, 4, 3));
	}

	public static int fastPower(long a, long b, long mod) {
		long res = 1; // a^b
		while (b > 0) {
			if ((b & 1) == 1) {
				res = res * a % mod;
			}
			a = a * a % mod;
			b >>= 1;
		}
		return (int) res;
	}

	public static int solve1(int[] A) {
		long mod = 1000000007;
		long n = A.length;
		Arrays.sort(A);
		long ans = 0;
		for (int i = 0; i < (int) n; i++) {
			long x = (fastPower(2, (int) (i), mod) - fastPower(2, (int) (n - i - 1), mod) + mod);
			x = ((x % mod) * A[i]) % mod;
			ans = (ans + x + mod) % mod;
		}
		while (ans < 0) {
			ans += mod;
		}
		ans = ans % mod;
		return (int) ans;

	}

	public static int solve(int[] A) {
		int[] B = A;
		Arrays.sort(B);
		int n = A.length;
		int ans = 0;
		for (int i = 0; i < n; i++) {
			int pos = binarySearch(A[i], B);
//			System.out.print("pos = " + pos + " ");
			int minc = Math.abs((n - 1) - pos) + 1;
			int maxc = pos + 1;
			System.out.print("minc = " + minc + " ");
			System.out.print("maxc = " + maxc + " ");
			int diff = Math.abs(minc - maxc);
			if (minc < maxc) {
				ans += A[i] * diff;
			} else {
				ans -= A[i] * diff;
			}
		}
		return ans;
	}

	public static int binarySearch(int x, int[] B) {
		int s = 0;
		int e = B.length - 1;
		int mid;
		while (s <= e) {
			mid = (s + e) / 2;
			if (B[mid] == x) {
				return mid;
			} else if (B[mid] > x) {
				e = mid - 1;
			} else {
				s = mid + 1;
			}
		}
		return -1;
	}

}
