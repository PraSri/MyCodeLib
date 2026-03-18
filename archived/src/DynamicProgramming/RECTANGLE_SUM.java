package DynamicProgramming;

public class RECTANGLE_SUM {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int solve(int[][] A) {

		int n = A.length;
		int m = A[0].length;

		int max = Integer.MIN_VALUE;

		for (int i = 0; i < n; i++) {
			int[] compArr = new int[n];
			for (int j = i; j < m; j++) {
				for (int k = 0; k < n; k++) {
					compArr[k] += A[k][j];
				}
				max = Math.max(max, kadane(compArr));
			}
		}

		return max;

	}

	private int kadane(int[] compArr) {
		int n = compArr.length;

		int currSum = 0;
		int maxSum = 0;
		for (int i = 0; i < n; i++) {

			currSum += compArr[i];
			if (currSum > maxSum) {
				maxSum = currSum;
			}
			if (currSum < 0) {
				currSum = 0;
			}

		}
		return maxSum;
	}

}
