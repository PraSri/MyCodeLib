package searchingAlgorithms;

public class KthSmallestTripletSum {

	public static void main(String[] args) {

	}

	/****
	 * Given an integer array A of size N.
	 * 
	 * If we store the sum of each triplet of the array A in a new list then find
	 * the Bth smallest element among the list.
	 * 
	 * NOTE: A triplet consist of three elements from the array. Let's say if A[i],
	 * A[j], A[k] are the elements of the triplet then i < j < k.
	 * 
	 * Problem Constraints
	 * 
	 * 3 <= N <= 500 1 <= A[i] <= 108 1 <= B <= (N*(N-1)*(N-2))/6
	 * 
	 * Example Input
	 * 
	 * Input 1:
	 * 
	 * A = [2, 4, 3, 2] B = 3
	 * 
	 * Input 2:
	 * 
	 * A = [1, 5, 7, 3, 2] B = 9
	 * 
	 * 
	 * 
	 * Example Output
	 * 
	 * Output 1:
	 * 
	 * 9
	 * 
	 * Output 2:
	 * 
	 * 14
	 * 
	 * 
	 * 
	 * Example Explanation
	 * 
	 * Explanation 1:
	 * 
	 * All the triplets of the array A are: (2, 4, 3) = 9 (2, 4, 2) = 8 (2, 3, 2) =
	 * 7 (4, 3, 2) = 9 So the 3rd smallest element is 9.
	 * 
	 * 
	 *****/

	/*
	 * 
	 * NOT ABLE TO UNDERSTAND SOLUTION
	 * 
	 */

	public static int solve(int[] A, int B) {

		int n = A.length;
		int s = 1, e = A[n - 3] + A[n - 2] + A[n - 1];
		int sum = -1;
		int mid;
		while (s <= e) {
			mid = (s + e) / 2;
			if (isPossible(A, B, mid)) {
				sum = mid;
				s = mid + 1;
			} else {
				e = mid - 1;
			}
		}
		return sum;

	}

	private static boolean isPossible(int[] a, int b, int mid) {
		int c = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = i + 1; j < a.length; j++) {
				int x = mid - a[i] - a[j];
				int k = j + 1;
				for (; k < a.length; k++) {
					if (a[k] >= x)
						break;
				}
				c = c + (k - 1) - j;
			}
		}

		if (c < b)
			return true;

		return false;
	}

}
