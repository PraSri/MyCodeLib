# Letter Combinations of a Phone Number

[LeetCode](https://leetcode.com/problems/letter-combinations-of-a-phone-number/) · Medium · Pattern: `backtracking`

- **Trigger Invariant:** Cartesian product of per-digit letter sets: depth = digit index, branching = letters on that key.
- **Edge Cases:** Empty digits → [] (not [""]); keys 7 and 9 have 4 letters; digits 0/1 are not in the input domain.
- **Complexity:** Time O(4^n · n) · Aux space O(n).
