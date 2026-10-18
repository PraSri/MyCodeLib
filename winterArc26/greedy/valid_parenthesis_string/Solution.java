package greedy.valid_parenthesis_string;

public class Solution {

    public boolean checkValidString(String s) {
        int lo = 0;
        int hi = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                lo++;
                hi++;
            } else if (c == ')') {
                lo--;
                hi--;
            } else {
                lo--;
                hi++;
            }
            if (hi < 0) {
                return false;
            }
            lo = Math.max(lo, 0);
        }
        return lo == 0;
    }
}
