# Distinct Subsequences

[LeetCode](https://leetcode.com/problems/distinct-subsequences/) · Hard · Pattern: `dp_two_sequences`

- **Trigger Invariant:** ways(i, j) for s[0..i), t[0..j): s[i−1] can always be skipped; if it equals t[j−1] it can also be matched. Rolling 1D array iterated over j DOWNWARD keeps row i−1 values.
- **Edge Cases:** t empty → 1; |t| > |s| → 0; intermediate counts can overflow int but only addition is used, so wrap-around still yields the correct (int-sized) final answer.
- **Complexity:** Time O(m · n) · Aux space O(n).
