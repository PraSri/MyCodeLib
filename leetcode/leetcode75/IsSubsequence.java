package leetcode75;

import java.util.Map;

public class IsSubsequence {

    public boolean isSubsequence(String s, String t) {
        int n = s.length();
        if (n == 0)
            return true;
        int m = t.length();
        int si = 0, ti = 0;
        while (ti < m) {
            if (t.charAt(ti) == s.charAt(si)) {
                si++;
                if (si == n)
                    return true;
            }
            ti++;
        }
        return false;
    }
}
