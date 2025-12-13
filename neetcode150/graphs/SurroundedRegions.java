package graphs;

public class SurroundedRegions {


        public void solve(char[][] board) {
            int n = board.length;
            int m = board[0].length;
            for(int i = 0;i<n;i++) {
                dfs(board, i, 0);
                dfs(board, i, m-1);
            }
            for(int i = 0;i<m;i++) {
                dfs(board, 0, i);
                dfs(board, n-1, i);
            }
            for(int i = 0;i<n;i++) {
                for(int j = 0;j<m;j++) {
                    if(board[i][j] == 'O') {
                        board[i][j] = 'X';
                    } else if(board[i][j] == '*') {
                        board[i][j] = 'O';
                    }
                }
            }
        }

        private void dfs(char[][] a, int i, int j) {
            if(i<0 || j<0 || i>=a.length || j>=a[0].length ||
                    a[i][j] == 'X' || a[i][j] == '*') {
                return;
            }
            a[i][j] = '*';
            dfs(a, i+1, j);
            dfs(a, i-1, j);
            dfs(a, i, j+1);
            dfs(a, i, j-1);
        }

}
