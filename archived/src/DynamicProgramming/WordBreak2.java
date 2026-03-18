package DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak2 {

    public static void main(String[] args) {
        List <String> dict= Arrays.asList("mobile","samsung","sam","sung",
                "man","mango", "icecream","and",
                "go","i","love","ice","cream");
        System.out.println("First Test:");
        String str1 = "iloveicecreamandmango";
        String str2 ="ilovesamsungmobile";
        wordBreak(dict, str1);
        System.out.println("######DP########");
        System.out.println(wordBreakDP(str1, new HashSet<>(dict)));
        System.out.println(dp);
    }

    // Prints all possible word breaks of given string
    static void wordBreak(List<String> dict, String s) {
        String ans = "";
        int n = s.length();
        wordBreakUtil(n, s, dict, ans);
    }

    static void wordBreakUtil(int n, String s, List<String> dict, String ans) {
        for (int i = 1; i <= n; i++) {

            // Extract substring from 0 to i in prefix
            String prefix = s.substring(0, i);

            // If dictionary contains this prefix, then
            // we check for remaining string.
            // Otherwise we ignore this prefix (there is no else for
            // this if) and try next
            if (dict.contains(prefix)) {
                // If no more elements are there, print it
                if (i == n) {
                    // Add this element to previous prefix
                    ans += prefix;
                    System.out.println(ans);
                    return;
                }
                wordBreakUtil(n - i, s.substring(i, n), dict, ans + prefix + " ");
            }
        }
    }


    static HashMap<Integer, List<String>> dp = new HashMap<>();

    public static List<String> wordBreakDP(String s, Set<String> wordDict) {
        int maxLength = -1;
        for(String ss : wordDict) maxLength = Math.max(maxLength, ss.length());
        return addSpaces(s, wordDict, 0, maxLength);
    }

    private static List<String> addSpaces(String s, Set<String> wordDict, int start, int max){
        List<String> words = new ArrayList<>();
        // base case
        if(start == s.length()) {
            words.add("");
            return words;
        }

        // recursive loop
        for(int i = start + 1; i <= max + start && i <= s.length(); i++){
            String temp = s.substring(start, i);
            if(wordDict.contains(temp)){
                List<String> ll;
                if(dp.containsKey(i)) ll = dp.get(i);
                else ll = addSpaces(s, wordDict, i, max);
                for(String ss : ll) words.add(temp + (ss.equals("") ? "" : " ") + ss);
            }
        }
        dp.put(start, words);
        return words;
    }


}
