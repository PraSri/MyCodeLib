# Validate Binary Search Tree

[LeetCode](https://leetcode.com/problems/validate-binary-search-tree/) · Medium · Pattern: `bst`

- **Trigger Invariant:** Each node must lie in an open interval (low, high) inherited from all ancestors — checking only the direct parent is not enough.
- **Edge Cases:** Node values equal to Integer.MIN_VALUE / MAX_VALUE (use long or nullable bounds); duplicates are invalid; violation deep in a subtree vs a grandparent.
- **Complexity:** Time O(n) · Aux space O(h).
