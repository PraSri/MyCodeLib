package trie.implement_trie_prefix_tree;

public class Solution {

    public static class Trie {

        private static final class Node {
            final Node[] next = new Node[26];
            boolean end;
        }

        private final Node root = new Node();

        public Trie() {
        }

        public void insert(String word) {
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
            Node node = walk(word);
            return node != null && node.end;
        }

        public boolean startsWith(String prefix) {
            return walk(prefix) != null;
        }

        private Node walk(String s) {
            Node node = root;
            for (int i = 0; i < s.length() && node != null; i++) {
                node = node.next[s.charAt(i) - 'a'];
            }
            return node;
        }
    }
}
