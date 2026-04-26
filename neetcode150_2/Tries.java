import java.util.*;

public class Tries {

    // Implement Trie (Prefix tree)
    public static class Trie {
        public Trie[] children;
        public boolean isEnd;

        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }
    }

    static class PrefixTree {
        private final Trie root;

        public PrefixTree() {
            root = new Trie();
        }

        public void insert(String word) {
            Trie node = root;
            for (char ch : word.toCharArray()) {
                if (node.children[ch - 'a'] == null) {
                    node.children[ch - 'a'] = new Trie();
                }
                node = node.children[ch - 'a'];
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            Trie node = searchPrefix(word);
            return node != null && node.isEnd;
        }

        public boolean startsWith(String prefix) {
            return searchPrefix(prefix) != null;
        }

        private Trie searchPrefix(String word) {
            Trie node = root;
            for (char ch : word.toCharArray()) {
                if (node.children[ch - 'a'] == null) {
                    return null;
                }
                node = node.children[ch - 'a'];
            }
            return node;
        }


    }

    static class WordDictionary {

        private final Trie root;
        private Boolean canFind;

        public WordDictionary() {
            root = new Trie();
            canFind = false;
        }

        public void addWord(String word) {

            Trie node = root;
            for (char ch : word.toCharArray()) {
                if (node.children[ch - 'a'] == null) {
                    node.children[ch - 'a'] = new Trie();
                }
                node = node.children[ch - 'a'];
            }
            if (node.isEnd) {
                return;
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            canFind = false;
            searchHelper(root, word, 0);
            return canFind;
        }

        private void searchHelper(Trie node, String word, int i) {
            if (canFind) return;
            if (node == null) return;
            int n = word.length();
            if (n == i) {
                if (node.isEnd) {
                    canFind = true;
                }
                return;
            }
            if (word.charAt(i) == '.') {
                for (int j = 'a'; j <= (int) 'z'; j++) {
                    searchHelper(node.children[(char) j - 'a'], word, i + 1);
                }
            } else {
                int index = word.charAt(i) - 'a';
                searchHelper(node.children[index], word, i + 1);
            }
        }
    }

    static class WordSearch {
        // word search II
        private Set<String> res;
        private boolean[][] visit;
        private int n, m;

        public List<String> findWords(char[][] board, String[] words) {
            TrieNode root = new TrieNode();
            for (String word : words) {
                root.addWord(word);
            }
            n = board.length;
            m = board[0].length;

            res = new HashSet<>();
            visit = new boolean[n][m];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    dfs(board, i, j, root, "");
                }
            }
            return new ArrayList<>(res);
        }

        private void dfs(char[][] board, int i, int j, TrieNode node, String word) {
            // boundary
            if (i < 0 || j < 0 || i >= n || j >= m ||
                    visit[i][j] || !node.children.containsKey(board[i][j])) return;

            visit[i][j] = true;
            node = node.children.get(board[i][j]);
            word += board[i][j];
            if (node.isWord) res.add(word);

            dfs(board, i + 1, j, node, word);
            dfs(board, i, j + 1, node, word);
            dfs(board, i - 1, j, node, word);
            dfs(board, i, j - 1, node, word);

            visit[i][j] = false;
        }

        static class TrieNode {
            Map<Character, TrieNode> children;
            boolean isWord;

            public TrieNode() {
                children = new HashMap<>();
                isWord = false;
            }

            public void addWord(String word) {
                TrieNode curr = this;
                for (char ch : word.toCharArray()) {
                    curr.children.putIfAbsent(ch, new TrieNode());
                    curr = curr.children.get(ch);
                }
                curr.isWord = true;
            }
        }
    }


}
