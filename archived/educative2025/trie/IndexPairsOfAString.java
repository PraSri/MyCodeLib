package trie;

import java.util.*;

public class IndexPairsOfAString {
    public static class Trie {
        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                node.children.putIfAbsent(ch, new TrieNode());
                node = node.children.get(ch);
            }
            node.isEndOfWord = true;
        }
    }

    public static class TrieNode {
        boolean isEndOfWord;
        HashMap<Character, TrieNode> children;

        public TrieNode() {
            this.isEndOfWord = false;
            this.children = new HashMap<>();
        }
    }


    public static class Solution {

        public static int[][] indexPairs(String text, String[] words) {
            // Initialize a Trie data structure to store the words
            Trie trie = new Trie();

            // Insert each word into the Trie
            for (String word : words) {
                trie.insert(word);
            }

            ArrayList<int[]> resultList = new ArrayList<>();

            // Loop through each character in the text
            for (int i = 0; i < text.length(); i++) {
                // Start from the root of the Trie for each character in the text
                TrieNode node = trie.root;

                // Check each possible substring starting from index i
                for (int j = i; j < text.length(); j++) {
                    char ch = text.charAt(j);
                    // If the character is not in the current Trie node's children, stop searching
                    if (!node.children.containsKey(ch)) {
                        break;
                    }

                    // Move to the next node (character) in the Trie
                    node = node.children.get(ch);

                    // If we reach the end of a word, record the indices
                    if (node.isEndOfWord) {
                        resultList.add(new int[]{i, j});
                    }
                }
            }

            // Convert ArrayList<int[]> to int[][]
            int[][] output = new int[resultList.size()][2];
            for (int i = 0; i < resultList.size(); i++) {
                output[i] = resultList.get(i);
            }

            // Return the list of index pairs representing matched words
            return output;
        }

        // Driver code
        public static void main(String[] args) {
            // Test cases
            String[] texts = {
                    "thestoryofeducative",
                    "ababa",
                    "helloworld",
                    "abcxyz",
                    "abcdef"
            };

            String[][] words = {
                    {"story", "of", "educ"},
                    {"aba", "ab"},
                    {"hello", "world"},
                    {"abc", "xyz"},
                    {"a", "abc", "def"}
            };

            // Loop through test cases
            for (int i = 0; i < texts.length; i++) {

                int[][] result = indexPairs(texts[i], words[i]);
                System.out.println((i + 1) + "\tText: " + texts[i]);
                System.out.print("\tWords: " + Arrays.toString(words[i]));

                System.out.println("\n\tResult: " + Arrays.deepToString(result));
                System.out.println(new String(new char[100]).replace('\0', '-'));
            }
        }
    }
}
