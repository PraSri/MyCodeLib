# Minimum Interval to Include Each Query

[LeetCode](https://leetcode.com/problems/minimum-interval-to-include-each-query/) · Hard · Pattern: `intervals`

- **Trigger Invariant:** Answer queries offline in increasing order: push every interval whose left ≤ q into a min-heap keyed by size, then pop intervals whose right < q (they can never cover a later query).
- **Edge Cases:** No covering interval → −1; duplicate queries (keep original indices); intervals of size 1 (right − left + 1).
- **Complexity:** Time O((n + q) log n + q log q) · Aux space O(n + q).
