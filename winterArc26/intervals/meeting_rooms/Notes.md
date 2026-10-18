# Meeting Rooms

[LeetCode](https://leetcode.com/problems/meeting-rooms/) · Easy · Pattern: `intervals`

- **Trigger Invariant:** Sorted by start, a conflict can only occur between neighbours: if any meeting starts before the previous one ends, one person can't attend all.
- **Edge Cases:** Empty / single meeting → true; back-to-back meetings (end == next start) are fine; unsorted input.
- **Complexity:** Time O(n log n) · Aux space O(log n) sort.
