# Sliding Window Maximum

[LeetCode](https://leetcode.com/problems/sliding-window-maximum/) · Hard · Pattern: `monotonic_deque`

- **Trigger Invariant:** An element is useless once a larger element arrives after it (it leaves the window earlier and is smaller), so keep indices with strictly decreasing values; the front is the max.
- **Edge Cases:** k == 1 → input; k == n → single max; duplicates (pop on <= keeps the newest); evict front when index ≤ i − k.
- **Complexity:** Time O(n) amortised · Aux space O(k).
