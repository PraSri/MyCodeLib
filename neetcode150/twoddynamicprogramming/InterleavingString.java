package twoddynamicprogramming;

public class InterleavingString {

    /***
     *
     *
     * Tumhe 3 strings diye gaye hain:
     *
     * s1
     *
     * s2
     *
     * s3
     *
     * Tumhe check karna hai ki kya s3, s1 aur s2 ko interleave karke ban sakta hai ya nahi.
     *
     * Interleaving ka Matlab?
     *
     * Interleaving ka matlab:
     *
     * s1 aur s2 ke characters order maintain karte hue unhe mix karke s3 banana
     *
     * Important:
     *
     * s1 ka internal order change nahi hona chahiye
     *
     * s2 ka internal order change nahi hona chahiye
     *
     * Bas dono ko merge karna hai
     *
     *
     * Sabse Pehla Check (Very Important) => length(s1) + length(s2) != length(s3) => Direct false
     *
     * */


    int n, m, N;

    Boolean dp[][];

    public boolean isInterleave(String s1, String s2, String s3) {
        n = s1.length();
        m = s2.length();
        N = s3.length();
        if (n + m != N) {
            return false;
        }
        dp = new Boolean[n + 1][m + 1];
        return dfs(0, 0, s1, s2, s3);
    }

    private boolean dfs(int i, int j, String s1, String s2, String s3) {
        if (i >= n && j >= m && i + j >= N) {
            return true;
        }
        if (i + j >= N) {
            return false;
        }
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        boolean result = false;

        /**
         * Agar:
         *
         * s1 ka current char
         *
         * s3 ke current char se match karta hai
         *
         * s1 ka index aage badhao
         * */
        if (i < n && s1.charAt(i) == s3.charAt(i + j)) {
            result = dfs(i + 1, j, s1, s2, s3);
        }
        /**
         *
         * jaise hi ek valid path mil gaya aur explore karne ki zarurat nahi
         * **/
        if (result) {
            return dp[i][j] = result;
        }

        /**
         * *
         * agar s2 ka char match kare
         * s2 ka index aage badhao
         * */
        if (j < m && s2.charAt(j) == s3.charAt(i + j)) {
            result = dfs(i, j + 1, s1, s2, s3);
        }
        return dp[i][j] = result;
    }

}
