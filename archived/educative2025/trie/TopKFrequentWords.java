package trie;

import java.util.*;

public class TopKFrequentWords {

    public static class TrieNode {
        TrieNode[] children;
        String word;

        public TrieNode() {
            children = new TrieNode[26];
            word = null;
        }
    }

    public static class Trie {
        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void addWord(String word) {
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                if (c >= 'a' && c <= 'z') {
                    if (cur.children[c - 'a'] == null) {
                        cur.children[c - 'a'] = new TrieNode();
                    }
                    cur = cur.children[c - 'a'];
                } else {
                    continue;
                }
            }
            cur.word = word;
        }

        public void getWords(TrieNode node, List<String> ans) {
            if (node == null) {
                return;
            }
            if (node.word != null) {
                ans.add(node.word);
            }
            for (int i = 0; i < 26; i++) {
                if (node.children[i] != null) {
                    getWords(node.children[i], ans);
                }
            }
        }
    }


    public static class Main {
        public static List<String> topKFrequentWords(String[] words, int k) {
            // Create a frequency map
            Map<String, Integer> frequencyMap = new HashMap<>();

            // Initialize buckets to hold words with same frequency
            Trie[] buckets = new Trie[words.length + 1];
            List<String> topK = new ArrayList<>();

            // Count the frequency of each word
            for (String word : words) {
                frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
            }

            // Organize words into buckets based on their frequency
            for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
                String word = entry.getKey();
                int frequency = entry.getValue();
                if (buckets[frequency] == null) {
                    buckets[frequency] = new Trie();
                }
                buckets[frequency].addWord(word);
            }

            // Iterate through the buckets in reverse order to get the most frequent words
            for (int i = buckets.length - 1; i >= 0 && topK.size() < k; i--) {
                if (buckets[i] != null) {
                    List<String> retrieveWords = new ArrayList<>();

                    // Retrieve words from the Trie in lexicographical order
                    buckets[i].getWords(buckets[i].root, retrieveWords);
                    if (retrieveWords.size() < k - topK.size()) {
                        // Add all words if the number of words is less than k
                        topK.addAll(retrieveWords);
                    } else {
                        // Add only the top k words
                        topK.addAll(retrieveWords.subList(0, k - topK.size()));
                    }
                }
            }

            return topK;
        }

        public static void generateFrequencyMap(String[] words) {
            Map<String, Integer> frequencyMap = new HashMap<>();

            for (String word : words) {
                frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
            }

            System.out.println("\n\tFrequency map: ");
            for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
                System.out.println("\t" + entry.getKey() + ": " + entry.getValue());
            }
        }

        public static void printWords(String[] words) {
            StringBuilder sb = new StringBuilder();
            System.out.print("[");

            for (int j = 0; j < words.length; j++) {
                System.out.print(words[j]);
                if (j < words.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }

        public static void main(String[] args) {
            String[][] words = {
                    {"apple", "banana", "orange", "banana", "banana"},
                    {"cat", "dog", "fish", "bird", "cat", "dog", "fish", "bird"},
                    {"python", "python", "python", "python", "python", "python", "python", "python", "python", "python"},
                    {"a", "b", "c", "a", "b", "a"},
                    {"tree", "bush", "flower", "tree", "bush", "tree", "rock", "rock", "grass"}
            };

            int[] k = {2, 4, 1, 3, 4};

            for (int i = 0; i < words.length; i++) {
                System.out.print((i + 1) + ".\tInput list: ");
                printWords(words[i]);
                generateFrequencyMap(words[i]);
                List<String> result = topKFrequentWords(words[i], k[i]);
                System.out.println("\n\tTop " + k[i] + " frequent word(s): " + result);
                System.out.println(String.join("", Collections.nCopies(100, "-")));
            }


        }
    }

}
