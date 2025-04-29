package trie;

import java.util.*;

public class WordSearchII {

    public static class TrieNode {
        Map<Character, TrieNode> children;
        boolean isString;

        TrieNode() {
            this.children = new HashMap<>();
            this.isString = false;
        }
    }

    public static class Trie {
        TrieNode root;

        Trie() {
            this.root = new TrieNode();
        }

        // Function to insert a string in the trie
        void insert(String stringToInsert) {
            TrieNode node = this.root;
            for (char c : stringToInsert.toCharArray()) {
                if (!node.children.containsKey(c)) {
                    node.children.put(c, new TrieNode());
                }
                node = node.children.get(c);
            }
            node.isString = true;
        }

        // Function to search a string from the trie
        boolean search(String stringToSearch) {
            TrieNode node = this.root;
            for (char c : stringToSearch.toCharArray()) {
                if (!node.children.containsKey(c)) {
                    return false;
                }
                node = node.children.get(c);
            }
            return node.isString;
        }

        // Function to search prefix of strings
        boolean startsWith(String prefix) {
            TrieNode node = this.root;
            for (char c : prefix.toCharArray()) {
                if (!node.children.containsKey(c)) {
                    return false;
                }
                node = node.children.get(c);
            }
            return true;
        }

        // Function to delete the characters in the searched word that are not shared
        void removeCharacters(String stringToDelete) {
            TrieNode node = this.root;
            List<TrieNode> childList = new ArrayList<>();

            for (char c : stringToDelete.toCharArray()) {
                childList.add(node);
                node = node.children.get(c);
            }

            for (int i = childList.size() - 1; i >= 0; i--) {
                TrieNode parent = childList.get(i);
                char childChar = stringToDelete.charAt(i);
                TrieNode target = parent.children.get(childChar);

                if (!target.children.isEmpty()) {
                    return;
                }
                parent.children.remove(childChar);
            }
        }
    }


    public static class WordSearch {
        public static List<String> findStrings(char[][] grid, String[] words) {
            Trie trieForWords = new Trie();
            List<String> result = new ArrayList<>();

            // Inserting strings in the dictionary
            for (String word : words) {
                trieForWords.insert(word);
            }

            // Calling dfs for all the cells in the grid
            for (int j = 0; j < grid.length; j++) {
                for (int i = 0; i < grid[0].length; i++) {
                    dfs(trieForWords, trieForWords.root, grid, j, i, result, "");
                }
            }

            return result;
        }

        private static void dfs(Trie wordsTrie, TrieNode node, char[][] grid, int row, int col, List<String> result, String word) {
            // Checking if we found the string
            if (node.isString) {
                result.add(word);
                node.isString = false;
                // remove the characters in the word that are not shared
                wordsTrie.removeCharacters(word);
            }

            if (0 <= row && row < grid.length && 0 <= col && col < grid[0].length) {
                char c = grid[row][col];
                // Getting child node of current node from Trie
                TrieNode child = node.children.get(c);
                // if child node exists in Trie
                if (child != null) {
                    String newWord = word + c;
                    // Marking it as visited before exploration
                    grid[row][col] = 0;
                    // Recursively calling DFS to search in all four directions
                    for (int[] offset : Arrays.asList(new int[]{0, 1}, new int[]{1, 0}, new int[]{0, -1}, new int[]{-1, 0})) {
                        dfs(wordsTrie, child, grid, row + offset[0], col + offset[1], result, newWord);
                    }
                    // Restoring state after exploration
                    grid[row][col] = c;
                }
            }
        }

        public static void printGrid(char[][] grid) {
            for (char[] chars : grid) {
                System.out.print("\t\t");
                for (int j = 0; j < grid[0].length; j++) {
                    if (j < grid[0].length - 1)
                        System.out.print("" + chars[j] + "   ");
                    else
                        System.out.print("" + chars[j] + "");
                }
                System.out.println();
            }
        }

        public static void main(String[] args) {


            char[][] grid0 = {{'B', 'S', 'L', 'I', 'M'},
                    {'R', 'I', 'L', 'M', 'O'},
                    {'O', 'L', 'I', 'E', 'O'},
                    {'R', 'Y', 'I', 'L', 'N'},
                    {'B', 'U', 'N', 'E', 'C'}};


            char[][] grid1 = {{'C', 'S', 'L', 'I', 'M'},
                    {'O', 'I', 'B', 'M', 'O'},
                    {'O', 'L', 'U', 'E', 'O'},
                    {'N', 'L', 'Y', 'S', 'N'},
                    {'S', 'I', 'N', 'E', 'C'}};

            char[][] grid2 = {{'C', 'O', 'L', 'I', 'M'},
                    {'I', 'N', 'L', 'M', 'O'},
                    {'A', 'L', 'I', 'E', 'O'},
                    {'R', 'T', 'A', 'S', 'N'},
                    {'S', 'I', 'T', 'A', 'C'}};

            char[][] grid3 = {{'P', 'S', 'L', 'A', 'M'},
                    {'O', 'P', 'U', 'R', 'O'},
                    {'O', 'L', 'I', 'E', 'O'},
                    {'R', 'T', 'A', 'S', 'N'},
                    {'S', 'I', 'T', 'A', 'C'}};

            char[][] grid4 = {{'O', 'A', 'A', 'N'},
                    {'E', 'T', 'A', 'E'},
                    {'I', 'H', 'K', 'R'},
                    {'I', 'F', 'L', 'V'}};

            char[][] grid5 = {{'S', 'T', 'R', 'A', 'C'},
                    {'I', 'R', 'E', 'E', 'E'},
                    {'N', 'G', 'I', 'T', 'C'},
                    {'I', 'T', 'S', 'R', 'A'}};

            char[][] grid6 = {{'A', 'A', 'A'},
                    {'A', 'A', 'A'},
                    {'A', 'A', 'A'}};

            char[][][] grid = {grid0, grid1, grid2, grid3, grid4, grid5, grid6};
            String[] strings1 = {"BUY", "SLICK", "SLIME", "ONLINE", "NOW"};
            String[] strings2 = {"BUY", "STUFF", "ONLINE", "NOW"};
            String[] strings3 = {"REINDEER", "IN", "RAIN"};
            String[] strings4 = {"TOURISM", "DESTINATIONS", "POPULAR"};
            String[] strings5 = {"OATH", "PEA", "EAT", "RAIN"};
            String[] strings6 = {"STREET", "STREETCAR", "STRING", "STING", "RING", "RACECAR"};
            String[] strings7 = {"A", "AA", "AAA", "AAAA"};
            String[][] words = {strings1, strings2, strings3, strings4, strings5, strings6, strings7};

            for (int i = 0; i < grid.length; i++) {
                System.out.println((i + 1) + ".\t2D grid: \n");
                printGrid(grid[i]);
                System.out.println("\n\tInput list: " + Arrays.toString(words[i]));
                System.out.println("\n\tOutput: " + findStrings(grid[i], words[i]));
                System.out.println(new String(new char[100]).replace('\0', '-'));
            }

        }
    }

}
