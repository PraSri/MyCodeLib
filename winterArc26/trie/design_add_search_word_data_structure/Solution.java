package trie.design_add_search_word_data_structure;

public class Solution {

    public static class WordDictionary {

        private static final class Node {
            final Node[] next = new Node[26];
            boolean end;
        }

        private final Node root = new Node();

        public WordDictionary() {
        }

        public void addWord(String word) {
            Node node = root;
            for (int i = 0; i < word.length(); i++) {
                int c = word.charAt(i) - 'a';
                if (node.next[c] == null) {
                    node.next[c] = new Node();
                }
                node = node.next[c];
            }
            node.end = true;
        }

        public boolean search(String word) {
            return dfs(word, 0, root);
        }

        private boolean dfs(String word, int i, Node node) {
            if (node == null) {
                return false;
            }
            if (i == word.length()) {
                return node.end;
            }
            char c = word.charAt(i);
            if (c != '.') {
                return dfs(word, i + 1, node.next[c - 'a']);
            }
            for (Node child : node.next) {
                if (dfs(word, i + 1, child)) {
                    return true;
                }
            }
            return false;
        }
    }
}
