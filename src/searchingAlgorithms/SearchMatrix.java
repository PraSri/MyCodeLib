package searchingAlgorithms;

public class SearchMatrix {

	public static void main(String[] args) {
	}

	public static int searchMatrix(int[][] A, int B) {

		int n = A.length;
		int m = A[0].length;

		for (int i = 0; i < n; i++) {
			if (B >= A[i][0] && B <= A[i][m - 1]) {
				if (binarySearch(A, B, i, m)) {
					return 1;
				}
			}
		}

		return 0;

	}

	private static boolean binarySearch(int[][] a, int b, int i, int m) {
		int s = 0;
		int e = m - 1;
		while (s <= e) {
			int mid = (s + e) / 2;
			if (a[i][mid] == b) {
				return true;
			} else if (a[i][mid] > b) {
				e = mid - 1;
			} else {
				s = mid + 1;
			}
		}
		return false;
	}

}
