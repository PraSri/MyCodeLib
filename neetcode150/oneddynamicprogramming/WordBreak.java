package oneddynamicprogramming;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/***Word Break II (Hard)
 https://leetcode.com/problems/word-break-ii/

 Extra Characters in a String (Medium)
 https://leetcode.com/problems/extra-characters-in-a-string/*/

public class WordBreak {

    Boolean[] dp;

    public static void main(String[] args) {
        WordBreak wordBreak = new WordBreak();
        Boolean ans = wordBreak.wordBreak("catsdog", List.of("cat", "dog", "catdo"));
        System.out.println(ans);
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        dp = new Boolean[n + 1];
        Arrays.fill(dp, null);
        dp[n] = true;
        return dfs(s, wordDict, 0);
    }

    //Time complexity: O(n * m * L) — where n = s.length(), m = wordDict.size(),
    // and L is the maximum word length; each of the O(n) start indices is processed once and
    // at each index you try all m words and compare up to L characters.
    //Space complexity: O(n) for the memo table (plus O(n) recursion stack in the worst case).
    private boolean dfs(String s, List<String> wordDict, int i) {
        if (dp[i] != null) {
            return dp[i];
        }
        for (String w : wordDict) {
            int cutLength = i + w.length();
            if (cutLength <= s.length() && s.substring(i, cutLength).equals(w)) {
                if (dfs(s, wordDict, cutLength)) {
                    dp[i] = true;
                    return true;
                }
            }
        }
        dp[i] = false;
        return false;
    }

    // s = "catdog" wordDict = ["cat", "dog", "catdo"]
    //
    // dfs(s, wordDict, 0)
    // for each word in wordDict:
    // cat -> i = 0, cutLen = 0 + cat.len = 3, catdog.len=6
    // if(3<=6 && catdog.substr(0, 3).equals(cat))
    // if(dfs(catdog, wordDict, 3))
    // dfs(catdog, wordDict, 3) => dfs(catdog, ["cat", "dog", "catdo"], 3)
    //


    // O(n^3)
    public boolean wordBreakAlgoMonster(String s, List<String> wordDict) {
        // Convert word list to HashSet for O(1) lookup
        Set<String> wordSet = new HashSet<>(wordDict);

        // Get the length of the input string
        int length = s.length();

        // dp[i] represents whether substring s[0...i-1] can be segmented into dictionary words
        // dp[0] is true (empty string can always be segmented)
        boolean[] dp = new boolean[length + 1];
        dp[0] = true;

        // Iterate through each position in the string
        for (int endIndex = 1; endIndex <= length; endIndex++) {
            // Check all possible starting positions for the current ending position
            for (int startIndex = 0; startIndex < endIndex; startIndex++) {
                // If substring s[0...startIndex-1] can be segmented (dp[startIndex] is true)
                // AND substring s[startIndex...endIndex-1] exists in the dictionary
                // Then substring s[0...endIndex-1] can also be segmented
                if (dp[startIndex] && wordSet.contains(s.substring(startIndex, endIndex))) {
                    dp[endIndex] = true;
                    break; // No need to check other starting positions
                }
            }
        }

        // Return whether the entire string can be segmented
        return dp[length];
    }


    /**
     * Word Break II
     * https://leetcode.com/problems/word-break-ii
     */
    public static class WordBreakIi {
    }

    /**
     * Extra Characters in a String
     * https://leetcode.com/problems/extra-characters-in-a-string
     */
    public static class ExtraCharactersInAString {
    }
}
