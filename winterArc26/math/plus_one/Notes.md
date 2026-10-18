# Plus One

[LeetCode](https://leetcode.com/problems/plus-one/) · Easy · Pattern: `math`

- **Trigger Invariant:** Carry only propagates through trailing 9s; the first non-9 digit from the right absorbs it. If every digit was 9, the answer is 1 followed by zeros.
- **Edge Cases:** All 9s ([9,9] → [1,0,0]); single digit; no leading zeros in input.
- **Complexity:** Time O(n) · Aux space O(1) (O(n) only for the all-9s case).
