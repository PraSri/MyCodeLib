# LRU Cache

[LeetCode](https://leetcode.com/problems/lru-cache/) · Medium · Pattern: `design`

- **Trigger Invariant:** HashMap gives O(1) lookup, a doubly linked list gives O(1) reorder/evict; invariant: list order == recency order, head side = most recent, tail side = eviction victim.
- **Edge Cases:** capacity == 1; put on an existing key must update value AND recency; get on a miss returns −1 and must not change order.
- **Complexity:** Time O(1) per op · Aux space O(capacity).
