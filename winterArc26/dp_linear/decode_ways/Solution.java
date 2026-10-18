package dp_linear.decode_ways;

public class Solution {

    public int numDecodings(String s) {
        int prev = 1;                         // ways(i - 2)
        int cur = s.charAt(0) == '0' ? 0 : 1; // ways(i - 1)
        for (int i = 2; i <= s.length(); i++) {
            int next = 0;
            if (s.charAt(i - 1) != '0') {
                next += cur;
            }
            int pair = (s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0');
            if (pair >= 10 && pair <= 26) {
                next += prev;
            }
            prev = cur;
            cur = next;
        }
        return cur;
    }
}
