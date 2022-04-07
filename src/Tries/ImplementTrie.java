// https://leetcode.com/problems/implement-trie-prefix-tree/


class Trie {
    
    TrieNode root;
    
    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode node = root;
        int n = word.length();
        for(int i = 0;i<n;i++) {
            char curr = word.charAt(i);
            if(node.next[curr-'a'] == null) {
                node.next[curr - 'a'] = new TrieNode();
            }
            node = node.next[curr - 'a'];
        }
        node.isEnd = true;
    }
    
    private TrieNode searchPrefix(String word) {
        TrieNode node = root;
        int n = word.length();
        for (int i = 0; i<n; i++) {
            char curr = word.charAt(i);
            if(node.next[curr - 'a'] != null) {
                node = node.next[curr-'a'];
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
    
    class TrieNode {
    private TrieNode[] next;
    private final int R = 26;
    
    private boolean isEnd;
    
    public TrieNode() {
        next = new TrieNode[R];
    }
    
    public boolean containsKey(char ch) {
        return next[ch-'a'] != null;
    }
    
    public TrieNode get(char ch) {
        return next[ch-'a'];
    }
    
    public void put(char ch, TrieNode node) {
        next[ch-'a'] = node;
    }
    
    
    public void setEnd() {
        isEnd = true;
    }
    
    public boolean isEnd() {
        return isEnd;
    }
    
    }
    
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
