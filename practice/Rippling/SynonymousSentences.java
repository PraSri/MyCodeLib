package Rippling;

import java.util.*;

public class SynonymousSentences {

    public static class UnionFind {
        private int[] parent;     // parent[i] holds the parent of i in the UF structure
        private int[] size;       // size[i] holds the size of the tree rooted at i

        // Constructor initializes each element's parent to itself and size to 1
        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; ++i) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        // find method with path compression, returns the root of the set x belongs to
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        // Union method to merge sets containing a and b
        public void union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA != rootB) {
                // Merge smaller tree into the larger one
                if (size[rootA] > size[rootB]) {
                    parent[rootB] = rootA;
                    size[rootA] += size[rootB];
                } else {
                    parent[rootA] = rootB;
                    size[rootB] += size[rootA];
                }
            }
        }
    }

    private List<String> answers = new ArrayList<>();  // List of all possible sentences
    private List<String> currentSentence = new ArrayList<>();  // Holds the current sentence during DFS
    private List<String> uniqueWords;  // List of unique words across all synonyms
    private Map<String, Integer> wordIdMap;  // Maps word to its index
    private UnionFind unionFind; // Union-Find instance for grouping synonyms
    private List<Integer>[] synonymGroups;  // Holds groups of synonym indices
    private String[] originalSentence;  // Original sentence split by whitespace

    // Main method to generate all possible sentences given a list of synonyms and a text string
    public List<String> generateSentences(List<List<String>> synonyms, String text) {
        Set<String> setOfSynonyms = new HashSet<>();  // Set to ensure unique words are collected
        for (List<String> pairs : synonyms) {
            setOfSynonyms.addAll(pairs);
        }
        uniqueWords = new ArrayList<>(setOfSynonyms);
        int wordCount = uniqueWords.size();
        wordIdMap = new HashMap<>(wordCount);

        // Populate the wordIdMap with each unique word's index
        for (int i = 0; i < uniqueWords.size(); ++i) {
            wordIdMap.put(uniqueWords.get(i), i);
        }

        unionFind = new UnionFind(wordCount);
        // Perform union operations for all pairs of synonyms
        for (List<String> pairs : synonyms) {
            unionFind.union(wordIdMap.get(pairs.get(0)), wordIdMap.get(pairs.get(1)));
        }

        // Initialize synonym groups
        synonymGroups = new List[wordCount];
        Arrays.setAll(synonymGroups, k -> new ArrayList<>());
        for (int i = 0; i < wordCount; ++i) {
            synonymGroups[unionFind.find(i)].add(i);
        }
        // Sort groups alphabetically based on the represented words
        for (List<Integer> group : synonymGroups) {
            group.sort((i, j) -> uniqueWords.get(i).compareTo(uniqueWords.get(j)));
        }

        // Split the original text into words
        originalSentence = text.split(" ");
        // Start DFS to build possible sentences
        dfs(0);
        return answers;
    }

    // Helper method to perform DFS and generate all possible sentences
    private void dfs(int index) {
        if (index >= originalSentence.length) {
            answers.add(String.join(" ", currentSentence));
            return;
        }
        // If current word has synonyms
        if (wordIdMap.containsKey(originalSentence[index])) {
            // Iterate through all synonyms
            for (int synonymIndex : synonymGroups[unionFind.find(wordIdMap.get(originalSentence[index]))]) {
                currentSentence.add(uniqueWords.get(synonymIndex));
                dfs(index + 1);
                currentSentence.remove(currentSentence.size() - 1);
            }
        } else {
            // No synonym for current word, add it as is
            currentSentence.add(originalSentence[index]);
            dfs(index + 1);
            currentSentence.remove(currentSentence.size() - 1);
        }
    }
}


