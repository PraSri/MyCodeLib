# Combination Sum

[LeetCode](https://leetcode.com/problems/combination-sum/) · Medium · Pattern: `backtracking`

- **Trigger Invariant:** Reuse is allowed, so recurse with the SAME start index after choosing i; using a non-decreasing start index prevents permutations of the same combination. Sorting lets you break once a candidate exceeds the remainder.
- **Edge Cases:** No combination possible → []; target smaller than every candidate; candidate equal to target.
- **Complexity:** Time O(n^(T/min)) worst case · Aux space O(T/min) recursion depth.
