package tries;

import java.util.*;

public class WordSearchII {

        static class TrieNode {
            Map<Character, TrieNode> children;
            boolean isWord;
            public TrieNode() {
                children = new HashMap<>();
                isWord = false;
            }

            public void addWord(String word) {
                TrieNode curr = this;
                for(char ch : word.toCharArray()) {
                    curr.children.putIfAbsent(ch, new TrieNode());
                    curr = curr.children.get(ch);
                }
                curr.isWord = true;
            }
        }
        private Set<String> res;
        private boolean[][] visit;
        private int n, m;

        public List<String> findWords(char[][] board, String[] words) {
            TrieNode root = new TrieNode();
            for(String word: words) {
                root.addWord(word);
            }
            n = board.length;
            m = board[0].length;
            res = new HashSet<>();
            visit = new boolean[n][m];
            for(int i = 0;i<n;i++) {
                for(int j = 0;j<m;j++) {
                    dfs(board, i, j, root, "");
                }
            }
            return new ArrayList<>(res);
        }

        private void dfs(char[][] board, int i , int j, TrieNode node, String word) {
            // boundary cases
            if(i < 0 || j < 0 || i>=n || j >= m || visit[i][j]
                    || !node.children.containsKey(board[i][j])) {
                return;
            }
            // mark visited
            visit[i][j] = true;
            // jump to next node
            node = node.children.get(board[i][j]);
            // update the word
            word += board[i][j];
            // if this is the full word present in Trie, add in results
            if(node.isWord) {
                res.add(word);
            }
            // explore all the directions
            dfs(board, i+1, j, node, word);
            dfs(board, i-1, j , node , word);
            dfs(board, i, j-1, node, word);
            dfs(board, i, j+1, node, word);
            // backtrack, unvisit the node
            visit[i][j] = false;
        }


}
