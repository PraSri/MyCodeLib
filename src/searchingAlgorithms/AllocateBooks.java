package searchingAlgorithms;

public class AllocateBooks {

	public static void main(String[] args) {

	}

	public static int books(int[] A, int B) {
		int n = A.length;
		int sum = 0;
		for (int x : A)
			sum += x;
		int s = 0;
		int e = sum;
		int res = Integer.MAX_VALUE;
		while (s < e) {
			int mid = (s + e) / 2;
			if (isPossible(A, n, B, mid)) {
				res = Math.min(mid, res);
				e = mid - 1;
			} else {
				s = mid + 1;
			}
		}
		return res == Integer.MAX_VALUE ? -1 : res;
	}

	private static boolean isPossible(int[] a, int n, int b, int cur_min) {
		int cur_sum = 0;
		int student_count = 1;
		for (int i = 0; i < n; i++) {
			if (a[i] > cur_min)
				return false;
			if (cur_sum + a[i] > cur_min) {
				student_count++;
				cur_sum = a[i];
				if (student_count > b) {
					return false;
				}
			} else {
				cur_sum += a[i];
			}
		}
		return true;
	}

}
