# Trapping Rain Water

[LeetCode](https://leetcode.com/problems/trapping-rain-water/) · Hard · Pattern: `two_pointers`

- **Trigger Invariant:** Water at i = min(maxLeft, maxRight) − h[i]; whichever side has the smaller running max is already the binding wall, so that side can be settled and advanced.
- **Edge Cases:** Fewer than 3 bars → 0; strictly increasing/decreasing → 0; plateaus of equal height.
- **Complexity:** Time O(n) · Aux space O(1).
