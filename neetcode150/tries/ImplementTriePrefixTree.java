package tries;


/**Design Add and Search Words Data Structure (Medium)
👉 https://leetcode.com/problems/design-add-and-search-words-data-structure/

Design Search Autocomplete System (Hard)
👉 https://leetcode.com/problems/design-search-autocomplete-system/

Replace Words (Medium)
👉 https://leetcode.com/problems/replace-words/

Implement Magic Dictionary (Medium)
👉 https://leetcode.com/problems/implement-magic-dictionary/

Encrypt and Decrypt Strings (Hard)
👉 https://leetcode.com/problems/encrypt-and-decrypt-strings/

Implement Trie II (Prefix Tree) (Medium)
👉 https://leetcode.com/problems/implement-trie-ii-prefix-tree/

Count Prefix and Suffix Pairs II (Hard)
👉 https://leetcode.com/problems/count-prefix-and-suffix-pairs-ii/

Count Prefix and Suffix Pairs I (Easy)
👉 https://leetcode.com/problems/count-prefix-and-suffix-pairs-i/*/

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


    /**
     * Design Add and Search Words Data Structure
     * https://leetcode.com/problems/design-add-and-search-words-data-structure
     */
    public static class DesignAddAndSearchWordsDataStructure {
    }

    /**
     * Design Search Autocomplete System
     * https://leetcode.com/problems/design-search-autocomplete-system
     */
    public static class DesignSearchAutocompleteSystem {
    }

    /**
     * Replace Words
     * https://leetcode.com/problems/replace-words
     */
    public static class ReplaceWords {
    }

    /**
     * Implement Magic Dictionary
     * https://leetcode.com/problems/implement-magic-dictionary
     */
    public static class ImplementMagicDictionary {
    }

    /**
     * Encrypt and Decrypt Strings
     * https://leetcode.com/problems/encrypt-and-decrypt-strings
     */
    public static class EncryptAndDecryptStrings {
    }

    /**
     * Implement Trie II (Prefix Tree)
     * https://leetcode.com/problems/implement-trie-ii-prefix-tree
     */
    public static class ImplementTrieIiPrefixTree {
    }

    /**
     * Count Prefix and Suffix Pairs II
     * https://leetcode.com/problems/count-prefix-and-suffix-pairs-ii
     */
    public static class CountPrefixAndSuffixPairsIi {
    }

    /**
     * Count Prefix and Suffix Pairs I
     * https://leetcode.com/problems/count-prefix-and-suffix-pairs-i
     */
    public static class CountPrefixAndSuffixPairsI {
    }
}
