package twoddynamicprogramming;


// Wildcard Matching - https://leetcode.com/problems/wildcard-matching/

public class RegularExpressionMatching {

    //string s[i...] kya pattern p[j...] se match karta hai ya nahi

    private Boolean[][] dp;

    public boolean isMatch(String s, String p) {
        int n = s.length(), m = p.length();
        //true/false batata hai ki
        //s ka index i se end tak
        //p ka index j se end tak match karta hai ya nahi.
        dp = new Boolean[n + 1][m + 1];
        return dfs(0, 0, s, p, n, m);
    }

    private Boolean dfs(int i, int j, String s, String p, int n, int m) {
        //Agar pattern khatam ho gaya:
        //
        //to string bhi khatam honi chahiye, tabhi match hoga
        //
        //warna false
        if (j == m) {
            return i == n;
        }

        if (dp[i][j] != null) {
            return dp[i][j];
        }

        //match = true hoga agar:
        //
        //string ka current char == pattern ka current char
        //
        //OR pattern me . ho (dot kisi bhi char se match karta hai)
        boolean match = i < n && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');

        // * case
        //* ka matlab:
        //
        //a* ? 0 ya zyada a
        //
        //Isliye 2 choices hoti hain:
        //1?? Zero occurrence
        //
        //a* ko ignore kar diya
        //
        //dfs(i, j+2)
        //2?? One or more occurrence
        //
        //agar current char match karta hai
        //
        //string aage badhao, pattern wahi rakho
        //
        //dfs(i+1, j)
        //Agar koi bhi option true ho jaaye ? match ho gaya
        if (j + 1 < m && p.charAt(j + 1) == '*') {
            // a* for example
            boolean zeroOccurrenceCase = dfs(i, j + 2, s, p, n, m); // a* ko ignore kr diya
            boolean oneOrMoreOccurrence = dfs(i + 1, j, s, p, n, m); // s = ab, p = a*, a match hogya, string agey bhadao aur pattern wahi rkho
            dp[i][j] = zeroOccurrenceCase || (match && oneOrMoreOccurrence);
        } else {
            // simple case
            // current char match hona chahiye
            //
            //dono pointers aage badhenge
            dp[i][j] = match && dfs(i + 1, j + 1, s, p, n, m);
        }
        return dp[i][j];
    }


    /**
     * Wildcard Matching
     * https://leetcode.com/problems/wildcard-matching/
     */
    public static class WildcardMatching {
    }
}
