package trie;

import java.util.*;

public class SearchSuggestionSystem {

    public static class Node {
        public Node[] child = new Node[26];
        public LinkedList<String> searchWords = new LinkedList<>();
    }

    private final Node root = new Node();

    private void insert(String word) {
        Node node = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (node.child[index] == null) {
                node.child[index] = new Node();
            }
            node = node.child[index];
            node.searchWords.offer(word);
            if (node.searchWords.size() > 3) {
                node.searchWords.pollLast();
            }
        }
    }

    private List<List<String>> search(String word) {
        List<List<String>> result = new ArrayList<>();
        Node node = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (node != null) {
                node = node.child[index];
            }
            result.add(node == null ? new ArrayList<>() : node.searchWords);
        }
        return result;
    }

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);
        Arrays.stream(products).forEach(this::insert);
        return search(searchWord);
    }

    public static void main(String[] args) {
        String[] products = {"bat", "bag", "bassinet", "bread", "cable",
                "table", "tree", "tarp"};
        String[] searchWordList = {"ba", "in", "ca", "t"};

        for (int i = 0; i < searchWordList.length; i++) {
            SearchSuggestionSystem obj = new SearchSuggestionSystem();
            System.out.println((i + 1) + ".\tProducts:" + Arrays.toString(products));
            System.out.println("\tSearch keyword: " + searchWordList[i]);
            System.out.println("\tSuggested Products: " + obj.suggestedProducts(products, searchWordList[i]));
            System.out.println("-".repeat(100));

        }
    }
}
