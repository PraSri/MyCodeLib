package oneddynamicprogramming;

import java.util.*;

public class WordBreak {

    Boolean[] dp;
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        dp = new Boolean[n+1];
        Arrays.fill(dp, null);
        dp[n] = true;
        return dfs(s, wordDict, 0);
    }

    private boolean dfs(String s, List<String> wordDict, int i) {
        if(dp[i]!=null) {
            return dp[i];
        }
        for(String w: wordDict) {
            if(i + w.length() <= s.length() &&
                    s.substring(i, i+w.length()).equals(w)) {
                if(dfs(s, wordDict, i + w.length())) {
                    dp[i] = true;
                    return true;
                }
            }
        }
        dp[i] = false;
        return false;
    }
}
