# Insert Interval

[LeetCode](https://leetcode.com/problems/insert-interval/) · Medium · Pattern: `intervals`

- **Trigger Invariant:** Input is sorted and disjoint, so intervals fall into three runs: entirely before newInterval, overlapping it (merge by min start / max end), entirely after.
- **Edge Cases:** Empty list; new interval before all / after all; touching endpoints ([1,2] + [2,3]) count as overlapping.
- **Complexity:** Time O(n) · Aux space O(1) besides the output.
