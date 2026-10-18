# Valid Parentheses

[LeetCode](https://leetcode.com/problems/valid-parentheses/) · Easy · Pattern: `stack`

- **Trigger Invariant:** Brackets must close in LIFO order; push the expected closer for every opener and require each closer to match the top.
- **Edge Cases:** Empty string → true; odd length → false; leading closer (empty stack); leftover openers at the end.
- **Complexity:** Time O(n) · Aux space O(n).
