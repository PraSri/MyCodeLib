package trie;

import java.util.*;

public class ReplaceWords {

    public static class TrieNode {
        public static final int SIZE = 26;
        TrieNode[] children = new TrieNode[SIZE];
        boolean isEOW;
    }

    public static class Trie {

        public TrieNode root;

        // Function to insert words in the trie
        public void insertInTrie(String key) {
            TrieNode temp = root;
            for (int i = 0; i < key.length(); i++) {
                int id = key.charAt(i) - 'a';
                if (Objects.isNull(temp.children[id])) {
                    temp.children[id] = new TrieNode();
                }
                temp = temp.children[id];
            }
            temp.isEOW = true;
        }

        public String replace(String str) {
            TrieNode temp = root;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                int id = str.charAt(i) - 'a';
                sb.append(str.charAt(i));
                if (Objects.isNull(temp.children[id])) {
                    return str;
                }
                temp = temp.children[id];
                if (temp.isEOW) {
                    return sb.toString();
                }
            }
            return sb.toString();
        }

    }

    public String replaceWords(String sentence, List<String> dictionary) {
        Trie trie = new Trie();
        trie.root = new TrieNode();

        // iterate over the prefixes in the dictionary, and
        // insert them in the trie

        // NOTE: insertInTrie function is implemented in the file, Trie.java
        for (String prefix : dictionary) {
            trie.insertInTrie(prefix);
        }

        // store each word of the sentence in a new list
        String[] sentanceList = sentence.split(" ");

        // this newList is intended to return the final sentence
        // after all possible replacements have been made
        List<String> newList = new ArrayList<>();

        // iterate over all the words in the sentence
        for (String str : sentanceList) {

            // replace each word in new_list with the
            // shortest prefix found from the trie

            // NOTE: replace function is implemented in the file, Trie.java
            newList.add(trie.replace(str));
        }

        //after replacing each word with the shortest matching prefix,
        // convert the list of words back to a single sentence
        return String.join(" ", newList);
    }

    // driver code
    public static void main(String[] args) {
        ReplaceWords s = new ReplaceWords();
        String[] sentence = {
                "where there is a will there is a way",
                "the quick brown fox jumps over the lazy dog",
                "oops there is no matching word in this sentence",
                "i was born on twenty ninth february",
                "i dont know where you are but i will find you eventually"
        };
        List<List<String>> dictionary = Arrays.asList(Arrays.asList("wi", "wa", "w"),
                Arrays.asList("qui", "f", "la", "d"),
                Arrays.asList("oops", "there", "is", "no", "matching", "word", "in", "this", "sentence"),
                Arrays.asList("wa", "w", "a", "ty", "nint", "nin", "n", "feb", "februa", "f"),
                Arrays.asList("cool", "how", "sunday", "sun", "x"));

        for (int i = 0; i < sentence.length; i++) {
            System.out.print(i + 1);
            System.out.println(".\tInput sentence: " + sentence[i] + "'");
            System.out.println("\tDictionary words: " + dictionary.get(i) + "'");
            System.out.println("\tAfter replacing words: " + s.replaceWords(sentence[i], dictionary.get(i)) + "'");
            System.out.println("-".repeat(100));
        }

    }
}


