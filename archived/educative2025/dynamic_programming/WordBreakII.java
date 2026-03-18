package dynamic_programming;

import java.util.*;

public class WordBreakII {

    public List<String> wordBreak(String s, List<String> wordDict) {
        List<List<String>> dp = new ArrayList<>(s.length() + 1);
        for (int i = 0; i <= s.length(); i++) {
            dp.add(new ArrayList<>());
        }
        // base case
        dp.get(0).add("");

        // for each substring of input string repeat this process
        for (int i = 1; i <= s.length(); i++) {
            String prefix = s.substring(0, i);
            List<String> temp = new ArrayList<>();

            // iterate over the current prefix and break it down into all possible suffices

            for (int j = 0; j < i; j++) {
                String suffix = prefix.substring(j);
                // check if this suffix exists in dict, if it does,
                // we know it is valid word and can be used as part of the solution
                if (wordDict.contains(suffix)) {
                    // retrieve valid sentences from previously computed subproblem
                    for (String substring : dp.get(j)) {
                        temp.add(substring + (substring.isEmpty() ? "" : " ") + suffix);
                    }
                }
            }

            dp.set(i, temp);
        }

        // return all the sentences formed by complete string s as prefix
        return dp.get(s.length());
    }

    public static void main(String[] args) {
        WordBreakII wb = new WordBreakII();
        List<String> ans = wb.wordBreak("vegan", List.of("veg", "vegan", "an"));
        System.out.println(ans);
    }


}
