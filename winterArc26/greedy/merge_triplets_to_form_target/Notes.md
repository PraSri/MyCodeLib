# Merge Triplets to Form Target Triplet

[LeetCode](https://leetcode.com/problems/merge-triplets-to-form-target-triplet/) · Medium · Pattern: `greedy`

- **Trigger Invariant:** Merging is element-wise max, so any triplet exceeding target in some coordinate is poison; among the safe ones, target is reachable iff each coordinate is hit exactly by someone.
- **Edge Cases:** A triplet equal to target → true; target coordinate never matched → false; single triplet.
- **Complexity:** Time O(n) · Aux space O(1).
