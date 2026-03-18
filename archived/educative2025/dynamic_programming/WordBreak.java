package dynamic_programming;

import java.util.*;

public class WordBreak {

    public static boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();

        // Create a set of words from the list so that the lookup cost in the set becomes O(1)
        Set<String> wordSet = new HashSet<>(wordDict);

        // Initialize the lookup table
        boolean[] dp = new boolean[n + 1];

        // Set the first element to true as it represents the empty string
        dp[0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                // Checking if the substring from j to i is present in the wordSet and dp[j] is true
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    // If a substring is found, no need to check further smaller substrings
                    break;
                }
            }
        }

        // Return the last element of dp array
        return dp[n];
    }

    public static void main(String[] args) {
        List<String> s = new ArrayList<>(Arrays.asList(
                "vegancookbook", "catsanddog", "highwaycrash",
                "pineapplepenapple", "screamicecream", "educativecourse"
        ));

        List<String> wordDict = new ArrayList<>(Arrays.asList(
                "ncoo", "kboo", "inea", "icec", "ghway", "and", "anco", "hi", "way", "wa",
                "amic", "ed", "cecre", "ena", "tsa", "ami", "lepen", "highway", "ples",
                "ookb", "epe", "nea", "cra", "lepe", "ycras", "dog", "nddo", "hway",
                "ecrea", "apple", "shp", "kbo", "yc", "cat", "tsan", "ganco", "lescr",
                "ep", "penapple", "pine", "book", "cats", "andd", "vegan", "cookbook"
        ));

        System.out.println("The list of words we can use to break down the strings are:\n\n"+ wordDict+"\n");

        System.out.println(new String(new char[100]).replace("\0", "-"));

        for (int i = 0; i < s.size(); ++i) {
            System.out.printf("Test Case #%d\n\n", i + 1);
            System.out.printf("Input: '%s'\n", s.get(i));
            Print.possibleCombinations(s.get(i), wordDict);
            System.out.printf("Output: %b\n", wordBreak(s.get(i), wordDict));
            System.out.println(new String(new char[100]).replace("\0", "-"));
        }
    }

    static public class Print {
        public static void possibleCombinations(String s, List<String> wordDict) {
            List<String> result = new ArrayList<>();
            findCombinations(s, wordDict, "", result);

            if (result.isEmpty()) {
                System.out.println("No combinations found.");
            } else {
                System.out.println("Possible combinations:");
                for (String combination : result) {
                    System.out.println(combination);
                }
            }
        }

        private static void findCombinations(String s, List<String> wordDict, String current, List<String> result) {
            if (s.isEmpty()) {
                result.add(current.trim());
                return;
            }

            for (String word : wordDict) {
                if (s.startsWith(word)) {
                    findCombinations(s.substring(word.length()), wordDict, current + " " + word, result);
                }
            }
        }
    }

}
