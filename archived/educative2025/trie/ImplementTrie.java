package trie;

public class ImplementTrie {
    TrieNode root;

    public ImplementTrie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        int n = word.length();
        for (int i = 0; i < n; i++) {
            char curr = word.charAt(i);
            if (node.next[curr - 'a'] == null) {
                node.next[curr - 'a'] = new TrieNode();
            }
            node = node.next[curr - 'a'];
        }
        node.isEnd = true;
    }

    private TrieNode searchPrefix(String word) {
        TrieNode node = root;
        int n = word.length();
        for (int i = 0; i < n; i++) {
            char curr = word.charAt(i);
            if (node.next[curr - 'a'] != null) {
                node = node.next[curr - 'a'];
            } else {
                return null;
            }
        }
        return node;
    }

    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd;
    }

    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }

    public static class TrieNode {
        private final TrieNode[] next;
        private boolean isEnd;

        public TrieNode() {
            int r = 26;
            next = new TrieNode[r];
        }

        public boolean containsKey(char ch) {
            return next[ch - 'a'] != null;
        }

        public TrieNode get(char ch) {
            return next[ch - 'a'];
        }

        public void put(char ch, TrieNode node) {
            next[ch - 'a'] = node;
        }


        public void setEnd() {
            isEnd = true;
        }

        public boolean isEnd() {
            return isEnd;
        }

    }

}
