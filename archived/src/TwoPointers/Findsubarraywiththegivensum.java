package TwoPointers;

public class Findsubarraywiththegivensum {

	public static void main(String[] args) {

		int[] A = new int[] { 1, 1000000000 };
		int B = 1000000000;

		int[] ans = solve(A, B);

		for (int x : ans) {
			System.out.print(x + " ");
		}

	}

	public static int[] solve(int[] A, int B) {
		int i = 0;
		int n = A.length;
		int j = 0;
		int sum = 0;
		int[] ans;
		while (i < n) {
			sum = A[i];
			if (sum == B) {
				ans = new int[1];
				ans[0] = A[i];
				return ans;
			}
			j = i + 1;
			while (j < n) {
				sum += A[j];
				if (sum == B) {
					int l = j - i + 1;
					ans = new int[l];
					int m = 0;
					for (int k = i; k <= j; k++) {
						ans[m] = A[k];
						m++;
					}
					return ans;
				}
				if (sum > B) {
					break;
				}
				j++;
			}
			i++;
		}
		ans = new int[1];
		ans[0] = -1;
		return ans;
	}

}
