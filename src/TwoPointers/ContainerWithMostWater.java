package TwoPointers;

public class ContainerWithMostWater {

	/**
	 * Given n non-negative integers A[0], A[1], ..., A[n-1] , where each represents
	 * a point at coordinate (i, A[i]).
	 * 
	 * N vertical lines are drawn such that the two endpoints of line i is at (i,
	 * A[i]) and (i, 0).
	 * 
	 * Find two lines, which together with x-axis forms a container, such that the
	 * container contains the most water.
	 * 
	 * Note: You may not slant the container.
	 ***/

	public static void main(String[] args) {
		int[] A = new int[] { 5, 1, 2 };
		System.out.println(maxArea(A));
	}

	public static int maxArea(int[] A) {
		int n = A.length;
		if (n == 1 || n == 0) {
			return 0;
		}
		int i = 0, j;
		int max = Integer.MIN_VALUE;
		int area;
		while (i < n) {
			j = i + 1;
			while (j < n) {
				area = Math.abs(Math.min(A[i], A[j]) * (j - i));
				if (area > max) {
					max = area;
				}
				j++;
			}
			i++;
		}
		return max;
	}

}
