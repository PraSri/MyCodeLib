# Car Fleet

[LeetCode](https://leetcode.com/problems/car-fleet/) · Medium · Pattern: `monotonic_stack`

- **Trigger Invariant:** Process cars from closest-to-target backwards; a car that would arrive no later than the fleet ahead merges into it, so fleets are the strictly increasing arrival times (a collapsed monotonic stack).
- **Edge Cases:** Single car → 1; equal arrival times merge (use > not ≥ for a new fleet); compute time as double to avoid integer-division truncation.
- **Complexity:** Time O(n log n) sort · Aux space O(n).
