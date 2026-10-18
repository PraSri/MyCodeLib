# Hand of Straights

[LeetCode](https://leetcode.com/problems/hand-of-straights/) · Medium · Pattern: `greedy`

- **Trigger Invariant:** The smallest remaining card can only be the START of a group, so greedily build the run smallest, smallest+1, … and fail on any gap.
- **Edge Cases:** n % groupSize ≠ 0 → false; groupSize = 1 → true; many duplicates (consume counts, not values).
- **Complexity:** Time O(n log n) · Aux space O(n).
