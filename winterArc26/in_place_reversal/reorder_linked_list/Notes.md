# Reorder List

[LeetCode](https://leetcode.com/problems/reorder-list/) · Medium · Pattern: `in_place_reversal`

- **Trigger Invariant:** L0→Ln→L1→Ln−1… = interleave the first half with the reversed second half; find the middle (fast/slow), reverse the back half in place, then zip.
- **Edge Cases:** 0/1/2 nodes (nothing to do); odd vs even length (first half keeps the extra node); must cut the halves (slow.next = null) to avoid a cycle.
- **Complexity:** Time O(n) · Aux space O(1).
