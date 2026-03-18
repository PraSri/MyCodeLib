package trie;

import java.util.*;

public class DesignAddSearchWordDataStructure {

    // TrieNode template class
    public static class TrieNode {
        // Empty list of child nodes
        public TrieNode[] children;
        public boolean complete;

        public TrieNode() {
            // Create 26 child nodes for each letter of alphabet
            children = new TrieNode[26];
            // False indicates this node is not the end of a word
            complete = false;
        }
    }

    public static class WordDictionary {
        TrieNode root;
        boolean canFind;

        // Initialize the root with TrieNode and set
        // the 'canFind' boolean to FALSE
        public WordDictionary() {
            root = new TrieNode();
            canFind = false;
        }

        // Function to get all words in the dictionary
        public List<String> getWords() {
            List<String> wordsList = new ArrayList<>();
            // Return an empty list if the root is NULL
            if (root == null) return new ArrayList<>();
            // Perform depth first search on the trie
            return DFS(root, "", wordsList);
        }

        private List<String> DFS(TrieNode node, String word, List<String> wordsList) {
            // If the node is NULL, return the 'wordsList'
            if (node == null) return wordsList;
            // If the word is complete, add it to the 'wordsList'
            if (node.complete) {
                wordsList.add(word);
            }
            for (int j = 'a'; j <= (int) 'z'; j++) {
                String prefix = word + (char) j;
                wordsList = DFS(node.children[j - 'a'], prefix, wordsList);
            }
            return wordsList;
        }

        // Function to add a new word to the dictionary
        public void addWord(String word) {
            int n = word.length();

            TrieNode curNode = root;
            for (int i = 0; i < n; i++) {
                // Find the correct index of the character in the list of nodes
                int index = word.charAt(i) - 'a';
                // If the letter is not present in the trie, then
                // create a new trie node for it,
                // otherwise use the existing trie node for this letter
                if (curNode.children[index] == null) {
                    curNode.children[index] = new TrieNode();
                }
                curNode = curNode.children[index];

                if (i == n - 1) {
                    // If we've reached the end of word and complete flag is already set
                    // this means that it is already present in the dictionary
                    if (curNode.complete) {
                        System.out.println("\tWord already present!");
                        return;
                    }
                    // Once all the characters are added to the trie,
                    // set the 'complete' variable to TRUE
                    curNode.complete = true;
                }
            }
            System.out.println("\tWord added successfully!");
        }

        // Function to search for a word in the dictionary
        public boolean search(String word) {
            // Set the 'canFind' variable as FALSE
            canFind = false;
            // Perform depth-first search to iterate over the children nodes
            searchHelper(root, word, 0);
            return canFind;
        }

        private void searchHelper(TrieNode node, String word, int i) {
            // If the word has already been found and there is no need
            // for further searching, return the control to the calling context
            if (canFind) return;
            // Return the control to the calling context
            // if the current node is empty
            if (node == null) return;

            int n = word.length();
            // If we have found the last character of the query string
            // in the trie and the complete flag is set, we have found the entire word
            if (n == i) {
                if (node.complete) {
                    canFind = true;
                }
                return;
            }
            // If the word contains a wildcard character ".", match
            // it with all of the children (letters) of the current node and
            // perform depth first search starting at each child
            if (word.charAt(i) == '.') {
                for (int j = 'a'; j <= (int) 'z'; j++) {
                    searchHelper(node.children[(char) j - 'a'], word, i + 1);
                }
            } else {
                // otherwise, simply locate the child corresponding to the current character
                int index = word.charAt(i) - 'a';
                // and continue the depth-first traversal
                searchHelper(node.children[index], word, i + 1);
            }
        }
    }

    static class Solution {
        public static void main(String[] args) {

            WordDictionary obj = new WordDictionary();

            // Adding words
            String[] words = {"add", "sky", "hello", "multi", "addition", "sky", "multiply", "table"};
            int i = 1;
            for (String w : words) {
                System.out.println(i + ".\tAdding word: '" + w + "'");
                obj.addWord(w);
                System.out.println("-".repeat(100));
                i += 1;
            }

            // Searching words
            String[] wordSearch = {"helo", "multiple", "...le", "..llo", "..r"};
            for (String v : wordSearch) {
                System.out.println(i + ".\tSearching word: '" + v + "'");
                System.out.println("\t" + obj.search(v));
                System.out.println("-".repeat(100));
                i += 1;
            }

            // Getting all words
            System.out.println(i + "\tGetting all words: " + String.join(", ", obj.getWords()));
        }
    }

}
