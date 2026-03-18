package Stack;

public class MaximumRectangle {

	public static void main(String[] args) {
		int A[][] = { { 0, 1, 1, 0 }, { 1, 1, 1, 1 }, { 1, 1, 1, 1 }, { 1, 1, 0, 0 }, };
		int AB[][] = { {0, 1, 0, 1 }, { 1, 0, 1, 0}};
		MaximumRectangle maximumRectangle = new MaximumRectangle();
		int ans = maximumRectangle.solve(AB);
		System.out.println(ans);
	}

	public int solve(int[][] A) {

		int max = LargestRectangleInHistogram.largestRectangleArea(A[0]);

		for (int i = 1; i < A.length; i++) {
			for (int j = 0; j < A[0].length; j++) {
				if (A[i][j] == 1) {
					A[i][j] += A[i - 1][j];
				}
			}
			max = Math.max(max, LargestRectangleInHistogram.largestRectangleArea(A[i]));
		}

		return max;
	}

}
