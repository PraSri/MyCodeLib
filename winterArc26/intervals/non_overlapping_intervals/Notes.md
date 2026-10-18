# Non-overlapping Intervals

[LeetCode](https://leetcode.com/problems/non-overlapping-intervals/) · Medium · Pattern: `intervals`

- **Trigger Invariant:** Maximise kept intervals = activity selection: sort by END and always keep the interval finishing earliest — it leaves the most room for the rest. Removals = n − kept.
- **Edge Cases:** Touching endpoints do NOT overlap ([1,2],[2,3]); identical intervals; sort comparator with Integer.compare (no subtraction overflow on negatives).
- **Complexity:** Time O(n log n) · Aux space O(log n) sort.
