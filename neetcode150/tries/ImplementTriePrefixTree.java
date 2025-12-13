package tries;

public class ImplementTriePrefixTree {

    static class Trie {
        public Trie[] children;
        public boolean isEnd;
        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }
    }

    private final Trie root;

    public ImplementTriePrefixTree() {
        root = new Trie();
    }

    public void insert(String word) {
        Trie node = root;
        for(char ch : word.toCharArray()) {
            if(node.children[ch - 'a'] == null) {
                node.children[ch - 'a'] = new Trie();
            }
            node = node.children[ch-'a'];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node!=null && node.isEnd;
    }

    public boolean startsWith(String prefix) {
        Trie node = searchPrefix(prefix);
        return node!=null;
    }

    private Trie searchPrefix(String word) {
        Trie node = root;
        for(char ch : word.toCharArray()) {
            if(node.children[ch - 'a'] == null) {
                return null;
            }
            node = node.children[ch - 'a'];
        }
        return node;
    }

}
