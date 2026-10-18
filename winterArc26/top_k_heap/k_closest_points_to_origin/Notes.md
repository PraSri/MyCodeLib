# K Closest Points to Origin

[LeetCode](https://leetcode.com/problems/k-closest-points-to-origin/) · Medium · Pattern: `top_k_heap`

- **Trigger Invariant:** Keep a max-heap (by distance) of size k; any point farther than the root can be discarded, so the heap ends holding exactly the k closest.
- **Edge Cases:** k == n; ties at equal distance (any order allowed); compare squared distances (no sqrt) — use long if coordinates can exceed ~46k.
- **Complexity:** Time O(n log k) · Aux space O(k).
