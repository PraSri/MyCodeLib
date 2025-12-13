package advancedgraphs;

import java.util.*;

/**
 * Alien Dictionary Problem - Find character ordering from sorted alien words.
 * 
 * Solution: Graph + Topological Sort (Kahn's Algorithm)
 * 
 * Topological sort is used for DAG (Directed Acyclic Graph) where each edge u -> v
 * means u comes before v in the ordering.
 * 
 * Approach:
 * 1. Build a directed graph from character ordering constraints
 * 2. Track in-degree count for each character
 * 3. Process nodes with in-degree 0 first (BFS)
 * 4. If all nodes are processed, we have a valid ordering; otherwise, a cycle exists
 * 
 * Time Complexity: O(C) where C is total length of all words
 * Space Complexity: O(U) where U is number of unique characters
 */
public class AlienDictionaryV2 {

    private final Map<Character, Set<Character>> adjacencyList;
    private final Map<Character, Integer> inDegree;
    private final Set<Character> uniqueCharacters;

    public AlienDictionaryV2() {
        this.adjacencyList = new HashMap<>();
        this.inDegree = new HashMap<>();
        this.uniqueCharacters = new LinkedHashSet<>();
    }

    /**
     * Determines the character ordering from a list of sorted alien words.
     *
     * @param words list of words sorted in alien dictionary order
     * @return string representing character order, or empty string if invalid
     */
    public String alienOrder(List<String> words) {
        if (words == null || words.isEmpty()) {
            return "";
        }

        // Initialize all unique characters
        initializeCharacters(words);

        // Build graph from word pair comparisons
        if (!buildGraph(words)) {
            return "";
        }

        // Apply topological sort to get character ordering
        return topologicalSort();
    }

    /**
     * Initializes unique characters and their in-degree counts from all words.
     */
    private void initializeCharacters(List<String> words) {
        for (String word : words) {
            for (char c : word.toCharArray()) {
                uniqueCharacters.add(c);
                inDegree.putIfAbsent(c, 0);
                adjacencyList.putIfAbsent(c, new HashSet<>());
            }
        }
    }

    /**
     * Builds the adjacency list by comparing consecutive word pairs.
     *
     * @return false if an invalid ordering is detected, true otherwise
     */
    private boolean buildGraph(List<String> words) {
        for (int i = 0; i < words.size() - 1; i++) {
            String currentWord = words.get(i);
            String nextWord = words.get(i + 1);

            // Validate: prefix word should not come after the longer word
            if (currentWord.length() > nextWord.length() 
                    && currentWord.startsWith(nextWord)) {
                return false;
            }

            // Find first differing character and add edge
            addEdgeFromDifference(currentWord, nextWord);
        }
        return true;
    }

    /**
     * Finds the first differing character between two words and adds a directed edge.
     */
    private void addEdgeFromDifference(String word1, String word2) {
        int minLength = Math.min(word1.length(), word2.length());
        
        for (int i = 0; i < minLength; i++) {
            char from = word1.charAt(i);
            char to = word2.charAt(i);

            if (from != to) {
                // Only add edge if it doesn't already exist (Set handles duplicates)
                if (adjacencyList.get(from).add(to)) {
                    inDegree.merge(to, 1, Integer::sum);
                }
                break; // Only first difference matters
            }
        }
    }

    /**
     * Performs Kahn's algorithm for topological sorting.
     *
     * @return ordered characters as string, or empty string if cycle detected
     */
    private String topologicalSort() {
        Queue<Character> queue = new ArrayDeque<>();
        StringBuilder result = new StringBuilder();

        // Start with all characters having in-degree 0
        for (char c : uniqueCharacters) {
            if (inDegree.get(c) == 0) {
                queue.offer(c);
            }
        }

        while (!queue.isEmpty()) {
            char current = queue.poll();
            result.append(current);

            // Reduce in-degree for all neighbors
            for (char neighbor : adjacencyList.getOrDefault(current, Collections.emptySet())) {
                int newDegree = inDegree.merge(neighbor, -1, Integer::sum);
                if (newDegree == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // Cycle detection: not all characters were processed
        if (result.length() < uniqueCharacters.size()) {
            return "";
        }

        return result.toString();
    }

    /**
     * Static convenience method for quick usage.
     */
    public static String alienOrderV2(List<String> words) {
        return new AlienDictionaryV2().alienOrder(words);
    }
}
