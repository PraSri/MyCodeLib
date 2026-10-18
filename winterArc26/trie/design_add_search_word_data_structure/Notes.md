# Design Add and Search Words Data Structure

[LeetCode](https://leetcode.com/problems/design-add-and-search-words-data-structure/) · Medium · Pattern: `trie`

- **Trigger Invariant:** A trie keeps exact characters cheap; '.' just branches the DFS over all 26 children at that depth — only wildcard positions fan out.
- **Edge Cases:** Pattern of all dots; pattern longer than any word; '.' at the last position must still require an end-of-word.
- **Complexity:** Time add O(L), search O(26^d · L) with d dots · Aux space O(total chars × 26).
