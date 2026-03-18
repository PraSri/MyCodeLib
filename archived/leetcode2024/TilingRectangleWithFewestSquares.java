package leetcode2024;

public class TilingRectangleWithFewestSquares {

  int ans = Integer.MAX_VALUE;

  public static void main(String[] args) {

  }

  // https://leetcode.com/problems/tiling-a-rectangle-with-the-fewest-squares/solutions/414979/java-back-tracking-solution/?envType=problem-list-v2&envId=55ac4kuc

  public int tilingRectangle(int n, int m) {
    dfs(0, 0, new boolean[n][m], 0);
    return ans;
  }

  private void dfs(int r, int c, boolean[][] rect, int ct) {

    int n = rect.length;
    int m = rect[0].length;

    if (ct >= ans) {
      return;
    }

    if (r >= n) {
      ans = ct;
      return;
    }

    if (c >= m) {
      dfs(r + 1, 0, rect, ct);
      return;
    }

    if (rect[r][c]) {
      dfs(r, c + 1, rect, ct);
      return;
    }

    for (int k = Math.min(n - r, m - c); k >= 1 && isAvail(rect, r, c, k); k--) {
      cover(rect, r, c, k);
      dfs(r, c + 1, rect, ct + 1);
      uncover(rect, r, c, k);
    }


  }

  private void uncover(boolean[][] rect, int r, int c, int k) {
    for (int i = 0; i < k; i++) {
      for (int j = 0; j < k; j++) {
        rect[r + i][c + j] = false;
      }
    }

  }

  private void cover(boolean[][] rect, int r, int c, int k) {
    for (int i = 0; i < k; i++) {
      for (int j = 0; j < k; j++) {
        rect[r + i][c + j] = true;
      }
    }
  }

  private boolean isAvail(boolean[][] rect, int r, int c, int k) {
    for (int i = 0; i < k; i++) {
      for (int j = 0; j < k; j++) {
        if (rect[r + i][c + j]) {
          return false;
        }
      }
    }

    return true;
  }


}
