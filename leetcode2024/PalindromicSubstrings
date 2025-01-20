package leetcode2024;

public class PalindromicSubstrings {

  public static void main(String[] args) {
    System.out.println("hello");
    System.out.println(new PalindromicSubstrings().countSubstrings("abc"));
    System.out.println(new PalindromicSubstrings().countSubstrings("aaa"));
    System.out.println(new PalindromicSubstrings().countSubstringsV2("abc"));
    System.out.println(new PalindromicSubstrings().countSubstringsV2("aaa"));
    System.out.println(new PalindromicSubstrings().countSubstringsV3("abc"));
    System.out.println(new PalindromicSubstrings().countSubstringsV3("aaa"));
    System.out.println(new PalindromicSubstrings().countSubstringsV4("abc"));
    System.out.println(new PalindromicSubstrings().countSubstringsV4("aaa"));
  }

  public int countSubstrings(String s) {

    // check for all the substrings
    int n = s.length();
    int ans = 0;
    for (int i = 0; i < n; i++) {
      for (int j = i; j < n; j++) {
        if (isPalindrome(s, i, j)) {
          ans++;
        }
      }
    }

    return ans;
  }

  private boolean isPalindrome(String s, int i, int j) {

    while (i < j) {
      if (s.charAt(i++) != s.charAt(j--)) {
        return false;
      }
    }
    return true;
  }

  public int countSubstringsV2(String s) {

    // check for all the substrings
    int n = s.length();
    int ans = 0;
    for (int i = 0; i < n; i++) {
      int odd = palindromeCount(s, i,
          i); // considering odd palindrome // center at i & add neighbours to check its palindrome
      int even = palindromeCount(s, i, i +
          1); // considering even palindrome // center at i & i+1 then  add neighbours to check its palindrome
      ans += even + odd;
    }

    return ans;
  }

  private int palindromeCount(String s, int left, int right) {
    int count = 0;
    while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
      left--;
      right++;
      count++;
    }
    return count;
  }


  public int countSubstringsV3(String s) {

    int n = s.length();

    boolean[][] dp = new boolean[n][n];
    int ans = 0;

    // len = 1
    for (int i = 0; i < n; i++) {
      dp[i][i] = true;
      ans++;
    }

    // len = 2
    for (int i = 0; i < n - 1; i++) {
      if (s.charAt(i) == s.charAt(i + 1)) {
        dp[i][i + 1] = true;
        ans++;
      }
    }

    // len > 3
    for (int len = 3; len <= n; len++) {
      for (int i = 0; i < n - len + 1; i++) {
        if (s.charAt(i) == s.charAt(i + len - 1) && dp[i + 1][i + len - 2]) {
          dp[i][i + len - 1] = true;
          ans++;
        }
      }
    }

    return ans;


  }

  public int countSubstringsV4(String s) {

    int n = s.length();

    boolean[][] dp = new boolean[n][n];
    int ans = 0;

    for (int len = 1; len <= n; len++) {
      for (int i = 0; i < n - len + 1; i++) {
        if (s.charAt(i) == s.charAt(i + len - 1) && (len <= 2 || dp[i + 1][i + len - 2])) {
          dp[i][i + len - 1] = true;
          ans++;
        }
      }
    }

    return ans;


  }


}
