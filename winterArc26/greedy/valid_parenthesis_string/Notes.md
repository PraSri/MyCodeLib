# Valid Parenthesis String

[LeetCode](https://leetcode.com/problems/valid-parenthesis-string/) · Medium · Pattern: `greedy`

- **Trigger Invariant:** Track the RANGE [lo, hi] of possible open counts; '*' widens it both ways. hi < 0 is fatal; lo is clamped at 0 (a '*' chosen as ')' can be reinterpreted). Valid iff lo == 0 at the end.
- **Edge Cases:** Empty → true; "*" alone → true; leading ')' → false; "(*)" vs "((*".
- **Complexity:** Time O(n) · Aux space O(1).
