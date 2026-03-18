package searchingAlgorithms;

public class Maximumheightofthestaircase {

	public static void main(String[] args) {
		System.out.println(" ans : " + solve(20));
	}

	public static int solve(int A) {
		int s = 1;
		int e = A;
		int mid = 1;
		int ans = 0;
		while (s <= e) {
			mid = s + (e - s) / 2;
			int x = mid * (mid + 1) / 2;
			int y = (mid - 1) * (mid) / 2;
//			System.out.print("x = " + x + " ");
			if (x > A && y <= A) {
				return mid - 1;
			} else if (x > A) {
				e = mid - 1;
			} else {
				s = mid + 1;
			}
		}
		return mid;
	}

}
