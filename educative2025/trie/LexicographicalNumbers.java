package trie;

import java.util.*;
import java.util.stream.Collectors;

public class LexicographicalNumbers {

    public static class TrieNode {
        TrieNode[] children = new TrieNode[100];
        boolean isEnd = false;
    }

    private final TrieNode root = new TrieNode();

    public void addNumber(String s) {
        TrieNode node = root;
        for (char c : s.toCharArray()) {
            if (node.children[c - '0'] == null) {
                node.children[c - '0'] = new TrieNode();
            }
            node = node.children[c - '0'];
        }
        node.isEnd = true;
    }

    public List<String> getNumbers() {
        List<String> results = new ArrayList<>();
        dfs(root, "", results);
        return results;
    }

    private void dfs(TrieNode node, String number, List<String> results) {
        if (node == null) return;
        if (node.isEnd) results.add(number);

        for (int i = '0'; i <= '9'; i++) {
            String prefix = number + (char) i;
            dfs(node.children[i - '0'], prefix, results);
        }
    }

    public List<Integer> lexicalOrder(int n) {
        for (int i = 1; i <= n; i++) {
            addNumber(String.valueOf(i));
        }
        return getNumbers().stream().map(Integer::valueOf).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        LexicographicalNumbers obj = new LexicographicalNumbers();
        System.out.println(obj.lexicalOrder(9));
    }
}
