package TwoPointers;

public class BackspaceStringCompare {

    // https://leetcode.com/problems/backspace-string-compare/

    public static boolean backspaceCompare(String s, String t) {

        int n = s.length() - 1;
        int m = t.length() - 1;

        // backspace count for s & t
        int bs = 0, bt = 0;

        int i = n, j = m;

        while (i >= 0 || j >= 0) {

            while (i >= 0) {
                if (s.charAt(i) == '#') {
                    bs++;
                    i--;
                } else if (bs > 0) {
                    bs--;
                    i--;
                } else {
                    break;
                }
            }

            while (j >= 0) {

                if (t.charAt(j) == '#') {
                    bt++;
                    j--;
                } else if (bt > 0) {
                    bt--;
                    j--;
                } else {
                    break;
                }

            }

            // after backspaces removed, the leftover string should be same, so to check at i,j have same chars

            if (i >= 0 && j >= 0 && s.charAt(i) != t.charAt(j)) {
                return false;
            }

            // if anyone string is exhausted

            if ((i >= 0) != (j >= 0)) {
                return false;
            }

            i--;
            j--;

        }

        return true;

    }

}
