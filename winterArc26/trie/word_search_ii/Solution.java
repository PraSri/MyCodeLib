package trie.word_search_ii;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    private static final class Node {
        final Node[] next = new Node[26];
        String word;
    }

    public List<String> findWords(char[][] board, String[] words) {
        Node root = new Node();
        for (String w : words) {
            Node node = root;
            for (char c : w.toCharArray()) {
                if (node.next[c - 'a'] == null) {
                    node.next[c - 'a'] = new Node();
                }
                node = node.next[c - 'a'];
            }
            node.word = w;
        }
        List<String> found = new ArrayList<>();
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                dfs(board, r, c, root, found);
            }
        }
        return found;
    }

    private void dfs(char[][] board, int r, int c, Node parent, List<String> found) {
        if (r < 0 || c < 0 || r == board.length || c == board[0].length) {
            return;
        }
        char ch = board[r][c];
        if (ch == '#' || parent.next[ch - 'a'] == null) {
            return;
        }
        Node node = parent.next[ch - 'a'];
        if (node.word != null) {
            found.add(node.word);
            node.word = null;
        }
        board[r][c] = '#';
        dfs(board, r + 1, c, node, found);
        dfs(board, r - 1, c, node, found);
        dfs(board, r, c + 1, node, found);
        dfs(board, r, c - 1, node, found);
        board[r][c] = ch;
    }
}
