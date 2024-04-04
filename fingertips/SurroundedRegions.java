package fingertips;

// Similar to https://github.com/PraSri/MyCodeLib/blob/master/src/Graphs/NumberOfIslands.java
class SurroundedRegions {
    public void solve(char[][] board) {
        solveV2(board);
    }

    public static void solveV2(char[][] A) {
		int n = A.length;
		int m = A[0].length;

// dfs on 1st & last column 
        for(int i =0; i<n; i++) {
            dfs(A, i, 0);
            dfs(A, i, m-1);
        }

// dfs on 1st & last row
        for(int i =0; i<m; i++) {
            dfs(A, 0, i);
            dfs(A, n-1, i);
        }


// make all remaining O to X
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (A[i][j] == 'O') {
					A[i][j] = 'X';
				}
                else if (A[i][j] == '*') {
					A[i][j] = 'O';
				}
			}
		}

	}

	private static void dfs(char[][] a, int i, int j) {

		// valid conditions to proceed with dfs
		if (i < 0 || j < 0 || i >= a.length || j >= a[0].length || a[i][j] == 'X' || a[i][j] == '*') {
			return ;
		}

		a[i][j] = '*';

		// do dfs in all 4 directions
		dfs(a, i - 1, j);// top
		dfs(a, i + 1, j);// below
		dfs(a, i, j + 1);// right
		dfs(a, i, j - 1);// left

	}
}
