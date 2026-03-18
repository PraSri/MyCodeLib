package trie;

import java.util.*;

public class PalindromePairs {

    static class TrieNode {
        int ending = -1;    // -1 means no word ends here
        Map<Character, TrieNode> next = new HashMap<>();
        List<Integer> prefixes = new ArrayList<>();
    }

    // Helper function to check if a substring of a word
    // (from index i to the end) is a palindrome
    static boolean isSuffixPalindrome(String s, int i) {
        for (int j = s.length() - 1; i < j; ++i, --j)
            if (s.charAt(i) != s.charAt(j))
                return false;
        return true;
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        // Initialize an empty Trie with a root node
        TrieNode trie = new TrieNode();

        // Iterate over the words array using the index variable to build the Trie
        for (int index = 0; index < words.length; index++) {
            String word = words[index];

            // Reverse the word
            String reversedWord = new StringBuilder(word).reverse().toString();

            // Initialize a Trie node pointer at the root of the trie
            TrieNode current = trie;

            // Traverse the reversed word character by character
            for (int j = 0; j < reversedWord.length(); j++) {
                // If the substring from the current position to the end is a palindrome
                if (isSuffixPalindrome(reversedWord, j))
                    current.prefixes.add(index);

                char c = reversedWord.charAt(j);
                // If the character does not exist in the current node’s next map
                current.next.putIfAbsent(c, new TrieNode());

                // Move current to the next Trie node based on the current character
                current = current.next.get(c);
            }

            // Mark the end of the word
            current.ending = index;
        }

        // Initialize an empty result list to store valid palindrome pairs
        List<List<Integer>> pairs = new ArrayList<>();

        // Iterate over the words array using the index variable
        for (int index = 0; index < words.length; index++) {
            String word = words[index];

            // Initialize a Trie node pointer at the root of the trie
            TrieNode current = trie;

            // For each character in word
            for (int j = 0; j < word.length(); j++) {
                // (Case 3) If current.ending is not -1 and not equal to index, and the
                // suffix of word starting from the current character is a palindrome
                if (current.ending != -1 && current.ending != index &&
                        isSuffixPalindrome(word, j))
                    pairs.add(Arrays.asList(index, current.ending));

                char c = word.charAt(j);

                // If the character is not found in current.next
                if (!current.next.containsKey(c)) {
                    current = null;
                    break;
                }

                // Move current to the next Trie node
                current = current.next.get(c);
            }

            // If the current is not NULL
            if (current != null) {
                // (Case 1) If current.ending is not -1 and not equal to index
                if (current.ending != -1 && current.ending != index)
                    pairs.add(Arrays.asList(index, current.ending));

                // (Case 2) Remaining suffixes are palindromes
                for (int prefix : current.prefixes)
                    pairs.add(Arrays.asList(index, prefix));
            }
        }

        return pairs;
    }

    // Driver code
    public static void main(String[] args) {
        String[][] arr = {
                {"od", "doll", "car"},
                {"mac", "polo", "cam"},
                {"x", ""},
                {"pqr", "fg", "gf", "eefg", "rpq"},
                {"v", "vv", "vvv"}
        };

        PalindromePairs sol = new PalindromePairs();
        for (int i = 0; i < arr.length; ++i) {
            System.out.print((i + 1) + "\twords: [");
            for (int j = 0; j < arr[i].length; ++j) {
                System.out.print("\"" + arr[i][j] + "\"");
                if (j < arr[i].length - 1)
                    System.out.print(", ");
            }

            List<List<Integer>> pairs = sol.palindromePairs(arr[i]);
            System.out.print("]\n\n\tValid palindrome pairs: [");
            for (int j = 0; j < pairs.size(); ++j) {
                System.out.print("[" + pairs.get(j).get(0) + ", " + pairs.get(j).get(1) + "]");
                if (j < pairs.size() - 1)
                    System.out.print(", ");
            }
            System.out.println("]");
            System.out.println("-".repeat(100));
        }
    }
}