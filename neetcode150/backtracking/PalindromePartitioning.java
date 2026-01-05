package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Palindrome Partitioning II (Hard)
 * ? https://leetcode.com/problems/palindrome-partitioning-ii/
 * <p>
 * Palindrome Partitioning IV (Hard)
 * ? https://leetcode.com/problems/palindrome-partitioning-iv/
 * <p>
 * Maximum Number of Non-overlapping Palindrome Substrings (Hard)
 * ? https://leetcode.com/problems/maximum-number-of-non-overlapping-palindrome-substrings/
 */
public class PalindromePartitioning {

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        dfs(s, 0, res, temp);
        return res;
    }

    private void dfs(String s, int start, List<List<String>> res, List<String> temp) {
        // base case, you reached the end of string
        if (start == s.length()) {
            res.add(new ArrayList<>(temp));
            return;
        }
        // try every possible cut starting from `start`
        for (int end = start; end < s.length(); end++) {
            if (isPali(s, start, end)) {
                temp.add(s.substring(start, end + 1));
                dfs(s, end + 1, res, temp);
                temp.remove(temp.size() - 1);
            }
        }
    }

    private boolean isPali(String s, int i, int j) {
        while (i < j)
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        return true;
    }

    /**
     * Palindrome Partitioning II
     * https://leetcode.com/problems/palindrome-partitioning-ii
     */
    public static class PalindromePartitioningIi {
    }

    /**
     * Palindrome Partitioning IV
     * https://leetcode.com/problems/palindrome-partitioning-iv
     */
    public static class PalindromePartitioningIv {
    }

    /**
     * Maximum Number of Non-overlapping Palindrome Substrings
     * https://leetcode.com/problems/maximum-number-of-non-overlapping-palindrome-substrings
     */
    public static class MaximumNumberOfNonOverlappingPalindromeSubstrings {
    }
}
