# Last Stone Weight

[LeetCode](https://leetcode.com/problems/last-stone-weight/) · Easy · Pattern: `top_k_heap`

- **Trigger Invariant:** Every round needs the current two largest; a max-heap gives them in O(log n) and re-inserts the difference.
- **Edge Cases:** Single stone → its weight; all stones destroyed → 0; equal heaviest pair leaves nothing.
- **Complexity:** Time O(n log n) · Aux space O(n).
