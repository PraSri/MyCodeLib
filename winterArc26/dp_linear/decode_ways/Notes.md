# Decode Ways

[LeetCode](https://leetcode.com/problems/decode-ways/) · Medium · Pattern: `dp_linear`

- **Trigger Invariant:** The last decoded token is a single digit 1–9 or a pair 10–26, so ways(i) = [valid single]·ways(i−1) + [valid pair]·ways(i−2).
- **Edge Cases:** Leading '0' → 0; "10"/"20" valid but "30", "00" invalid; '0' only decodes as part of 10 or 20.
- **Complexity:** Time O(n) · Aux space O(1).
