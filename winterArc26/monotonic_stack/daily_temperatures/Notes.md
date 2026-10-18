# Daily Temperatures

[LeetCode](https://leetcode.com/problems/daily-temperatures/) · Medium · Pattern: `monotonic_stack`

- **Trigger Invariant:** Keep a stack of days still waiting for a warmer day (temperatures non-increasing); a warmer day resolves every colder day on top in one pop each.
- **Edge Cases:** Strictly decreasing temps → all 0; equal temps are not 'warmer' (pop on strict <); last day always 0.
- **Complexity:** Time O(n) amortised · Aux space O(n).
