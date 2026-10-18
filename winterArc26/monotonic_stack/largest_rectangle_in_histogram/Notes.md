# Largest Rectangle in Histogram

[LeetCode](https://leetcode.com/problems/largest-rectangle-in-histogram/) · Hard · Pattern: `monotonic_stack`

- **Trigger Invariant:** With an increasing stack, when bar j is popped by a shorter bar i, the new top is its nearest smaller on the left and i its nearest smaller on the right — so its maximal width is known exactly.
- **Edge Cases:** Single bar; all equal heights; strictly increasing (flushed by a sentinel height 0 at i == n); zeros.
- **Complexity:** Time O(n) · Aux space O(n).
