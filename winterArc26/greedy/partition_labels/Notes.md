# Partition Labels

[LeetCode](https://leetcode.com/problems/partition-labels/) · Medium · Pattern: `greedy`

- **Trigger Invariant:** A part containing char c must extend to c's last occurrence; extend the current end to the max last-index seen, and cut when i reaches it.
- **Edge Cases:** All distinct chars → n parts of size 1; a single char repeated → one part; first and last chars equal → one part.
- **Complexity:** Time O(n) · Aux space O(26).
