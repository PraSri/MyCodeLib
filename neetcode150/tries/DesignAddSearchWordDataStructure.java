package tries;

public class DesignAddSearchWordDataStructure {

    public static class TrieNode {

        // Empty list of child nodes
        public TrieNode[] children;
        public boolean complete;

        public TrieNode() {
            // Create 26 child nodes for each letter of alphabet
            children = new TrieNode[26];

            // False indicates this node is not the end of a word
            complete = false;
        }
    }

    TrieNode root;
    boolean canFind;

    // Initialize the root with TrieNode and set
    // the 'canFind' boolean to FALSE
    public DesignAddSearchWordDataStructure() {
        root = new TrieNode();
        canFind = false;
    }

    public void addWord(String word) {

        int n = word.length();

        TrieNode curNode = root;

        for (int i = 0; i < n; i++) {

            // Find the correct index of the character in the list of nodes
            int index = word.charAt(i) - 'a';

            // If the letter is not present in the trie, then
            // create a new trie node for it,
            // otherwise use the existing trie node for this letter
            if (curNode.children[index] == null) {
                curNode.children[index] = new TrieNode();
            }
            curNode = curNode.children[index];

            if (i == n - 1) {
                // If we've reached the end of word and complete flag is already set
                // this means that it is already present in the dictionary
                if (curNode.complete) {
                    System.out.println("\tWord already present!");
                    return;
                }
                // Once all the characters are added to the trie,
                // set the 'complete' variable to TRUE
                curNode.complete = true;
            }
        }
        System.out.println("\tWord added successfully!");
    }

    public boolean search(String word) {
        // Set the 'canFind' variable as FALSE
        canFind = false;
        // Perform depth-first search to iterate over the children nodes
        searchHelper(root, word, 0);
        return canFind;
    }

    private void searchHelper(TrieNode node, String word, int i) {
        // If the word has already been found and there is no need
        // for further searching, return the control to the calling context
        if (canFind)
            return;
        // Return the control to the calling context
        // if the current node is empty
        if (node == null)
            return;

        int n = word.length();
        // If we have found the last character of the query string
        // in the trie and the complete flag is set, we have found the entire word
        if (n == i) {
            if (node.complete) {
                canFind = true;
            }
            return;
        }
        // If the word contains a wildcard character ".", match
        // it with all of the children (letters) of the current node and
        // perform depth first search starting at each child
        if (word.charAt(i) == '.') {
            for (int j = 'a'; j <= (int) 'z'; j++) {
                searchHelper(node.children[(char) j - 'a'], word, i + 1);
            }
        } else {
            // otherwise, simply locate the child corresponding to the current character
            int index = word.charAt(i) - 'a';
            // and continue the depth-first traversal
            searchHelper(node.children[index], word, i + 1);
        }
    }
}
