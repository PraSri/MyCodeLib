# Alien Dictionary

[LeetCode](https://leetcode.com/problems/alien-dictionary/) · Hard · Pattern: `topological_sort`

- **Trigger Invariant:** Only the FIRST differing character of each adjacent word pair yields an ordering edge; topologically sort the letters — a cycle or leftover means no valid order.
- **Edge Cases:** Prefix violation ["abc", "ab"] → ""; single word (all its letters, any order); letters that appear but have no edges must still be output.
- **Complexity:** Time O(C) total characters · Aux space O(1) (≤ 26 nodes, 26² edges).
