package TwoPointers;

public class Countofrectangleswitharealessthanthegivennumber {

	public static void main(String[] args) {
		int[] A = new int[] { 1, 2, 3, 4, 5 };
		int B = 5;
		System.out.println(solve(A, B));
	}

	public static int solve(int[] A, int B) {
		int n = A.length;
		int i = 0, j = n - 1;
		long area = 0, c = 0;
		long m = 1000000007;
		long x = B;
		while (i <= j) {
			area = A[i] * A[j];
			if (area < B) {
				c = (c + 2 * (j - i) + 1) % m;
				i++;
			} else
				j--;
		}
		return (int) c;
	}

}
