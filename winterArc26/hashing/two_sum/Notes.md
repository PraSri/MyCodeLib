# Two Sum

[LeetCode](https://leetcode.com/problems/two-sum/) · Easy · Pattern: `hashing`

- **Trigger Invariant:** For each x the partner is fixed (target − x); store value→index of everything to the left and look the partner up before inserting x (prevents using the same element twice).
- **Edge Cases:** Duplicates like [3,3] target 6 (lookup before insert handles it); negatives; target − x can overflow only if inputs hit ±2^31 boundaries.
- **Complexity:** Time O(n) · Aux space O(n).
