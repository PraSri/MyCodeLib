package searchingAlgorithms;

public class SpecialInteger {

	public static void main(String[] args) {
		int[] a = new int[] { 1, 2, 3, 4, 5 };
		int b = 10;
		System.out.println(" solve = " + solve1(a, b));
	}

	public static int solve(int[] A, int B) {
		int n = A.length;
		int s = 0, i;
		int e = n;
		int m;
		int ans = 0;
		int sum;
		while (s <= e) {
			m = s + (e - s) / 2;
			sum = 0;
			for (i = 0; i < m; ++i) {
				sum += A[i];
				if (sum > B) {
					break;
				}
			}
			if (s <= B) {
				for (i = m; i < n; ++i) {
					sum -= A[i - m];
					sum += A[i];
					if (sum > B) {
						break;
					}
				}
			}
			if (s > B) {
				e = m - 1;
			} else {
				ans = m;
				System.out.print("ans = " + ans + " Sum= " + sum);
				s = m + 1;
			}
		}
		return ans;

	}

	public static int solve1(int[] arr, int x) {
		int n = arr.length;
		int i, mid, ans = 0;
		int start = 0;
		int end = n;
		int s;
		while (start <= end) {
			mid = start + (end - start) / 2;
			s = 0;
			for (i = 0; i < mid; ++i) {
				s += arr[i];
				if (s > x) {
					break;
				}
			}
			if (s <= x) {
				for (i = mid; i < n; ++i) {
					s -= arr[i - mid];
					s += arr[i];
					if (s > x)
						break;
				}
			}

			if (s > x) {
				end = mid - 1;
			} else {
				ans = mid;
//				System.out.print("ans = " + ans + " Sum= " + s);
				start = mid + 1;
			}
		}
		return ans;

	}

}
