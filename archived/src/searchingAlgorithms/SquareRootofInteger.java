package searchingAlgorithms;

public class SquareRootofInteger {

	public static void main(String[] args) {

	}

	public static int sqrt(int A) {
		int n = A;
		if (A == 0 || A == 1) {
			return A & 1;
		}
		int s = 0;
		int e = n;
		int ans = 0;
		while (s <= e) {
			int mid = (s + e) / 2;
			if (mid == n / mid) {
				ans = mid;
				break;
			}
			if (mid < n / mid) {
				s = mid + 1;
				ans = mid;
			} else {
				e = mid - 1;
			}
		}
		return ans;
	}

}
