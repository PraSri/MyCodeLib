package backtracking.palindrome_partitioning;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public List<List<String>> partition(String s) {
        int n = s.length();
        boolean[][] pal = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                pal[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 2 || pal[i + 1][j - 1]);
            }
        }
        List<List<String>> result = new ArrayList<>();
        backtrack(s, 0, pal, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(String s, int start, boolean[][] pal, List<String> path, List<List<String>> result) {
        if (start == s.length()) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int end = start; end < s.length(); end++) {
            if (!pal[start][end]) {
                continue;
            }
            path.add(s.substring(start, end + 1));
            backtrack(s, end + 1, pal, path, result);
            path.remove(path.size() - 1);
        }
    }
}
