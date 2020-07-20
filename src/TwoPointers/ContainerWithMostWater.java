package TwoPointers;

public class ContainerWithMostWater {

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
