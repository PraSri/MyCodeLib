package DynamicProgramming;

public class ShortestCommonSupersequence {

  public static void main(String[] args) {
    ShortestCommonSupersequence scs = new ShortestCommonSupersequence();
    System.out.println(scs.shortestCommonSupersequence("abac", "cab"));
    printLCS("abac", "cab");
  }

  public static String printSCS(String x, String y) {

    int n = x.length();
    int m = y.length();

    int[][] lcs = getLCSMatrix(x, y);

    int l = lcs[n][m];
    int k = m + n - l;
    char[] s = new char[k + 1];
    s[k] = '$';
    int i = n, j = m;
    while (i > 0 && j > 0) {
      if (x.charAt(i - 1) == y.charAt(j - 1)) {
        s[k - 1] = x.charAt(i - 1);
        i--;
        j--;
      } else if (lcs[i - 1][j] > lcs[i][j - 1]) {
        s[k - 1] = x.charAt(i - 1);
        i--;
      } else {
        s[k - 1] = y.charAt(j - 1);
        j--;
      }
      k--;
    }

    while (i > 0) {
      s[k - 1] = x.charAt(i - 1);
      i--;
      k--;
    }

    while (j > 0) {
      s[k - 1] = y.charAt(j - 1);
      j--;
      k--;
    }

    return String.valueOf(s);

  }

  public static int[][] getLCSMatrix(String x, String y) {
    int n = x.length();
    int m = y.length();

    int[][] lcs = new int[n + 1][m + 1];

    for (int i = 0; i <= n; i++) {
      for (int j = 0; j <= m; j++) {
        if (i == 0 || j == 0) {
          lcs[i][j] = 0;
        } else if (x.charAt(i - 1) == y.charAt(j - 1)) {
          lcs[i][j] = 1 + lcs[i - 1][j - 1];
        } else {
          lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
        }
      }
    }

    return lcs;

  }

  public static void printLCS(String x, String y) {

    int n = x.length();
    int m = y.length();

    int[][] lcs = getLCSMatrix(x, y);

    int l = lcs[n][m];
    int k = l;
    char[] s = new char[k + 1];
    s[k] = '$';
    int i = n, j = m;
    while (i > 0 && j > 0) {
      if (x.charAt(i - 1) == y.charAt(j - 1)) {
        s[k - 1] = x.charAt(i - 1);
        i--;
        j--;
        k--;
      } else if (lcs[i - 1][j] > lcs[i][j - 1]) {
        i--;
      } else {
        j--;
      }
    }

    System.out.println(String.valueOf(s));

  }

  public String shortestCommonSupersequence(String str1, String str2) {

    // scs = m+n-lcs
    String s = printSCS(str1, str2);
    return s.substring(0, s.length() - 1);

  }
}
