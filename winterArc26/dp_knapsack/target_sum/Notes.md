# Target Sum

[LeetCode](https://leetcode.com/problems/target-sum/) · Medium · Pattern: `dp_knapsack`

- **Trigger Invariant:** Split into P (+) and N (−): P − N = target and P + N = total ⇒ P = (total + target)/2, so count subsets summing to P (0/1 knapsack counting, downward loop).
- **Edge Cases:** |target| > total → 0; (total + target) odd → 0; zeros double the count (handled naturally by the loop).
- **Complexity:** Time O(n · P) · Aux space O(P).
