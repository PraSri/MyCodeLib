package backtracking.generate_parentheses;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(new char[2 * n], 0, 0, n, result);
        return result;
    }

    private void backtrack(char[] buf, int open, int close, int n, List<String> result) {
        int pos = open + close;
        if (pos == buf.length) {
            result.add(new String(buf));
            return;
        }
        if (open < n) {
            buf[pos] = '(';
            backtrack(buf, open + 1, close, n, result);
        }
        if (close < open) {
            buf[pos] = ')';
            backtrack(buf, open, close + 1, n, result);
        }
    }
}
