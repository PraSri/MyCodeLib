# Time Based Key-Value Store

[LeetCode](https://leetcode.com/problems/time-based-key-value-store/) · Medium · Pattern: `binary_search`

- **Trigger Invariant:** Timestamps per key arrive strictly increasing, so each key's list is already sorted; get = upper-bound search for the last timestamp ≤ query.
- **Edge Cases:** Unknown key → ""; query earlier than first timestamp → ""; exact timestamp match.
- **Complexity:** Time set O(1), get O(log n) · Aux space O(n).
