package BacktrackingRecursion;

public class UniquePathsIII {

	public static void main(String[] args) {

	}

	/*
	 * Idea: Since the requirement is to touch every empty cell exactly once, we
	 * need dfs and maintain a step count count for the recursive path and when we
	 * reach destination, we compare how many empty cells we covered. To avoid
	 * cycles in the path, we mark the cells in the current dfs path as blockers and
	 * remove them while coming back.
	 */
	
	public static int solve(int[][] A) {
		// cout no. of zeroes
		int n = A.length;
		int m = A[0].length;
		int x = 0, y = 0;
		int ct = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (A[i][j] == 0) {
					ct++;
				} else if (A[i][j] == 1) {
					x = i;
					y = j;
				}
			}
		}

		int res;
		// x , y is starting point
		res = dfs(A, x, y, ct, -1);
		return res;
	}

	private static int dfs(int[][] a, int i, int j, int ct, int curCt) {

		if (i < 0 || i == a.length || j < 0 || j == a[0].length || a[i][j] == -1) {
			return 0;
		}

		if (a[i][j] == 2) {
			if (curCt == ct) {
				return 1;
			} else {
				return 0;
			}
		}

		a[i][j] = -1;
		int total = dfs(a, i + 1, j, ct, curCt + 1);
		total += dfs(a, i - 1, j, ct, curCt + 1);
		total += dfs(a, i, j + 1, ct, curCt + 1);
		total += dfs(a, i, j - 1, ct, curCt + 1);
		a[i][j] = 0;

		return total;
	}

}
