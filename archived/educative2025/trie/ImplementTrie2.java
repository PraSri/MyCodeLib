package trie;

import java.util.*;

public class ImplementTrie2 {
    public static class TrieNode {
        boolean isWord;
        HashMap<Character, TrieNode> children;

        public TrieNode() {
            this.children = new HashMap<>();
            this.isWord = false;
        }
    }

    public static class Trie {
        TrieNode root;

        // Constructor to create a trie node.
        public Trie() {
            this.root = new TrieNode();
        }

        // A function to insert a word in trie.
        public void insert(String word) {
            TrieNode trieNode = this.root;

            // Iterate over the word character by character.
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                // If child node is not present, create a new node
                // and add to trie.
                if (!trieNode.children.containsKey(c)) {
                    trieNode.children.put(c, new TrieNode());
                }
                trieNode = trieNode.children.get(c);
            }

            // Once the a complete word is added to the trie,
            // set boolean variable to true.
            trieNode.isWord = true;
        }

        // A function to search for a word in the trie.
        public boolean search(String word) {
            TrieNode trieNode = this.root;

            // Iterate over the word character by character.
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                // If there is no any child of a node,
                // return false as we do not find the word.
                if (!trieNode.children.containsKey(c)) {
                    return false;
                }

                trieNode = trieNode.children.get(c);
            }

            // Return the word after traversing all nodes as it is found.
            return trieNode.isWord;
        }

        // A function to search for a prefix of a word in the trie.
        public boolean searchPrefix(String prefix) {
            TrieNode trieNode = this.root;

            // Iterate over the prefix character by character.
            for (int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);

                // If there is no any child of a node,
                // return false as we do not find the prefix.
                if (!trieNode.children.containsKey(c)) {
                    return false;
                } else trieNode = trieNode.children.get(c);
            }

            // Return true after traversing all nodes as the prefix is found.
            return true;
        }

        // Driver Code
        public static void main(String args[]) {
            List<String> keys = Arrays.asList("the", "a", "there", "answer");
            Trie trieOfKeys = new Trie();
            int num = 1;
            for (String x : keys) {
                System.out.println(num + ".\tInserting key: '" + x + "'");
                trieOfKeys.insert(x);
                num += 1;
                System.out.println(new String(new char[100]).replace('\0', '-'));
            }
            List<String> search = Arrays.asList("a", "answer", "xyz", "an");
            for (String y : search) {
                System.out.println(num + ".\tSearching key: '" + y + "'");
                System.out.println("\tKey found? " + trieOfKeys.search(y));
                num += 1;
                System.out.println(new String(new char[100]).replace('\0', '-'));
            }
            List<String> searchPrefix = Arrays.asList("b", "an");
            for (String z : search) {
                System.out.println(num + ".\tSearching prefix: '" + z + "'");
                System.out.println("\tPrefix found? " + trieOfKeys.searchPrefix(z));
                num += 1;
                System.out.println(new String(new char[100]).replace('\0', '-'));
            }

            System.out.println("printState - ");
            Print.printState(trieOfKeys.root, "-");
        }
    }

    public static class Print {
        public static void printState(TrieNode trieNode, String indent) {
            Stack<TrieNode> stack = new Stack<TrieNode>();
            Stack<Character> keys = new Stack<Character>();
            TrieNode temp = trieNode;
            for (Character i : temp.children.keySet()) {
                stack.push(temp.children.get(i));
                keys.push(i);
            }
            while (!stack.isEmpty()) {
                System.out.print(indent);
                System.out.print(keys.pop());
                indent += "   ";
                temp = stack.pop();
                if (temp.isWord) System.out.print("*");
                while (!temp.children.isEmpty()) {
                    for (Character i : temp.children.keySet()) {
                        System.out.print(indent);
                        System.out.print(i);
                        temp = temp.children.get(i);
                        if (temp.isWord) System.out.print("*");
                        indent += "   ";

                    }

                }
                indent = "\n\t\t\t";

            }
        }
    }

}
