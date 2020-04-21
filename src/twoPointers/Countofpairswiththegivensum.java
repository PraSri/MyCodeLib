package twoPointers;

public class Countofpairswiththegivensum {

	public static void main(String[] args) {
		int[] A = new int[] { 1, 2, 3, 4, 5 };
		int B = 5;
		System.out.println(solve(A, B));
	}

	public static int solve(int[] A, int B) {
		int n = A.length;
		int i = 0;
		int j = n - 1;
		int sum = 0;
		int c = 0;
		while (i < j) {
			sum = A[i] + A[j];
			if (sum == B) {
				c++;
				i++;
				j--;
			} else if (sum > B) {
				j--;
			} else {
				i++;
			}
		}
		return c;
	}

}
