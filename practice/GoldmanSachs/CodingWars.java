package GoldmanSachs;

public class CodingWars {
//https://leetcode.com/problems/count-number-of-teams/

	// For each soldier, count how many soldiers on the left and right have less and
	// greater ratings.

// This soldier can form less[left] * greater[right] + greater[left] * less[right] teams.

	public int numTeams(int[] arr) {

		int count = 0;

		int len = arr.length;

		for (int j = 0; j < len; j++) {

			int leftSmaller = 0, rightLarger = 0;
			int leftLarger = 0, rightSmaller = 0;

			// travel left side of j in range [0,j)
			for (int i = 0; i < j; i++) {

				if (arr[i] < arr[j]) {
					leftSmaller++;
				} else if (arr[i] > arr[j]) {
					leftLarger++;
				}

			}

			// travel right side of j in range [j+1, n)
			for (int k = j + 1; k < len; k++) {
				if (arr[j] < arr[k]) {
					rightLarger++;
				} else if (arr[j] > arr[k]) {
					rightSmaller++;
				}
			}

			count += (leftSmaller * rightLarger) + (leftLarger * rightSmaller);
		}

		return count;
	}
}
