# Word Search II

[LeetCode](https://leetcode.com/problems/word-search-ii/) · Hard · Pattern: `trie`

- **Trigger Invariant:** Put all words in a trie and DFS the grid once per cell, walking the trie in lock-step; a path that leaves the trie can be abandoned immediately, so all words are searched simultaneously.
- **Edge Cases:** Same word found via two paths (clear node.word after recording); cells can't be reused in one word (mark '#'); prune emptied trie branches for speed.
- **Complexity:** Time O(m·n·4·3^(L−1)) · Aux space O(total chars in words).
