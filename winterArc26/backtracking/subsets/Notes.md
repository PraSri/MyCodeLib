# Subsets

[LeetCode](https://leetcode.com/problems/subsets/) · Medium · Pattern: `backtracking`

- **Trigger Invariant:** Every element is an independent include/exclude decision; a DFS that records the path at every node of the 'start index' tree emits each of the 2^n subsets exactly once.
- **Edge Cases:** Empty input → [[]]; single element; copy the path when recording (never add the live list).
- **Complexity:** Time O(n · 2^n) · Aux space O(n) recursion (output excluded).
