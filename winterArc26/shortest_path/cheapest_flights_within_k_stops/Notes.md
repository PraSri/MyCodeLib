# Cheapest Flights Within K Stops

[LeetCode](https://leetcode.com/problems/cheapest-flights-within-k-stops/) · Medium · Pattern: `shortest_path`

- **Trigger Invariant:** Bellman-Ford's i-th relaxation round yields the cheapest paths using ≤ i edges; run exactly k + 1 rounds, relaxing from a snapshot of the previous round so a round can't chain edges.
- **Edge Cases:** src == dst → 0; unreachable → −1; k = 0 (direct flights only); guard MAX_VALUE + price overflow.
- **Complexity:** Time O(k · E) · Aux space O(n).
