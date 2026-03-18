package tree_bfs;

import java.util.*;

public class WordLadder {
    public static int wordLadder(String src, String dest, List<String> words) {
        // Create a set of words for faster lookup
        Set<String> mySet = new HashSet<>(words);
        // If the dest is not in the set, return 0
        if (!mySet.contains(dest))
            return 0;
        // Create a queue for the words to be checked
        Queue<String> q = new LinkedList<>();
        q.offer(src);
        // Initialize the length counter
        int length = 0;

        // Check words until the queue is empty
        while (!q.isEmpty()) {
            length++;
            // Store the length of the queue
            int size = q.size();
            // Check all the words in the current level
            for (int i = 0; i < size; i++) {
                // Pop a word from the queue
                String curr = q.poll();
                // Iterate on each character of the popped word
                for (int j = 0; j < curr.length(); j++) {
                    String alpha = "abcdefghijklmnopqrstuvwxyz";
                    // Iterate with all possible characters
                    for (int k = 0; k < alpha.length(); k++) {
                        // Create a new word by replacing the jth character of the popped word
                        char[] temp = curr.toCharArray();
                        temp[j] = alpha.charAt(k);
                        String newWord = new String(temp);
                        // Check if the new word is the dest
                        if (newWord.equals(dest))
                            return length + 1;
                        // Check if the new word is in the set
                        // If it is, push it into the queue and remove it from the set
                        if (mySet.contains(newWord)) {
                            q.offer(newWord);
                            mySet.remove(newWord);
                        }
                    }
                }
            }
        }
        // Return 0 if no sequence exists
        return 0;
    }

    // Driver code
    public static void main(String[] args) {
        List<List<String>> wordsList = Arrays.asList(
                Arrays.asList("hog", "dot", "pot", "pop", "mop", "map", "cap", "cat"),
                Arrays.asList("hot", "dot", "lot", "log", "cog"),
                Arrays.asList("hot", "not", "dot", "lot", "cog"),
                Arrays.asList("hog", "dot", "pot", "pop", "mop", "map", "cap", "cat"),
                Arrays.asList("hot", "dot", "lot", "log", "cog", "com", "cam", "frog")
        );
        List<String> srcList = Arrays.asList("dog", "hit", "hat", "dog", "dog");
        List<String> destList = Arrays.asList("cat", "cog", "log", "cat", "frog");

        for (int i = 0; i < srcList.size(); i++) {
            System.out.println((i + 1) + ".\tsrc: \"" + srcList.get(i) + "\"");
            System.out.println("\tdest: \"" + destList.get(i) + "\"");
            System.out.print("\tAvailable words: ");
            System.out.println(wordsList.get(i));
            System.out.println("\n\tLength of shortest chain is: " + wordLadder(srcList.get(i), destList.get(i), wordsList.get(i)));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}

