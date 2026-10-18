# Implement Trie (Prefix Tree)

[LeetCode](https://leetcode.com/problems/implement-trie-prefix-tree/) · Medium · Pattern: `trie`

- **Trigger Invariant:** Shared prefixes share a path; a word is a path ending at a node flagged end-of-word, while a prefix only needs the path to exist.
- **Edge Cases:** search("app") after only inserting "apple" → false but startsWith → true; empty string; re-inserting the same word.
- **Complexity:** Time O(L) per op · Aux space O(total chars × 26).
