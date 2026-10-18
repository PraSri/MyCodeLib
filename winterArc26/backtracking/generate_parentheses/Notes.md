# Generate Parentheses

[LeetCode](https://leetcode.com/problems/generate-parentheses/) · Medium · Pattern: `backtracking`

- **Trigger Invariant:** A prefix is extendable to a valid string iff open ≤ n and close ≤ open; only branching on those two legal moves means no invalid string is ever built.
- **Edge Cases:** n = 1 → ["()"]; output size is the Catalan number C(n); reuse a char[] buffer instead of string concatenation.
- **Complexity:** Time O(4^n / √n) · Aux space O(n).
