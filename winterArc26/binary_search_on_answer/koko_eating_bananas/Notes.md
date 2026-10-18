# Koko Eating Bananas

[LeetCode](https://leetcode.com/problems/koko-eating-bananas/) · Medium · Pattern: `binary_search_on_answer`

- **Trigger Invariant:** Feasibility(k) = hours(k) ≤ h is monotone in k (faster never hurts), so binary-search the smallest feasible speed in [1, max(piles)].
- **Edge Cases:** h == piles.length → max pile; one pile; hours sum overflows int for big piles/small k (accumulate in long).
- **Complexity:** Time O(n log M), M = max pile · Aux space O(1).
