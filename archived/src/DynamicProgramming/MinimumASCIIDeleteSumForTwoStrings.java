package DynamicProgramming;

import java.util.Arrays;

public class MinimumASCIIDeleteSumForTwoStrings {
  int[][] dp;

  public static void main(String[] args) {

  }

  /**
   * case 1 : some ith and jth character matches in both string
   * case 2 : it doesn't matches
   *
   * so we have two casses , lets us analyze them one by one
   * case 1 : if some ith and jth character matches then we can reduce the ASCII sum if we include both ith and jth character in sequence, so we dont add their's ASCII values
   * case 2 : ith and jth character doesn't matches , so we have 2 option for these
   * 	option 1 : skip ith character assuming jth character might be useful later on , so we add ASCII of ith and recurr for rest
   * 	option 2 : skip jth character assuming ith matches somewhere late in string , same as option 1 for other string
   * */

  public int minimumDeleteSum(String s1, String s2) {
    dp = new int[s1.length() + 1][s2.length() + 1];
    Arrays.stream(dp).forEach(rows -> Arrays.fill(rows, -1));
    return sub(s1, s2, 0, 0);
  }

  private int sub(String s1, String s2, int i, int j) {
    int l1 = s1.length();
    int l2 = s2.length();
    int sum = 0;

    // base cases
    if (i == l1 || j == l2) {
      if (i == l1 && j == l2) {
        return 0;
      }
      return i == l1 ? deadEndSum(s2, j) : deadEndSum(s1, i);
    }

    if(dp[i][j]!=-1){
      return dp[i][j];
    }

    // 2 options
    if(s1.charAt(i) == s2.charAt(j)) {
      sum = sub(s1,s2,i+1,j+1);
    }else{
      int sumi = sub(s1,s2,i+1,j) + (int) s1.charAt(i);
      int sumj = sub(s1,s2,i,j+1) + (int) s2.charAt(j);
      sum = Math.min(sumi, sumj);
    }

    dp[i][j] = sum;
    return sum;

  }

  private int deadEndSum(String s, int i) {
    int sum = 0;
    while (i < s.length()) {
      sum += s.charAt(i);
      i++;
    }
    return sum;
  }

}
