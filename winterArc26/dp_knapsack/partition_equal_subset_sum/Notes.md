# Partition Equal Subset Sum

[LeetCode](https://leetcode.com/problems/partition-equal-subset-sum/) · Medium · Pattern: `dp_knapsack`

- **Trigger Invariant:** Equal halves ⇔ some subset sums to total/2: 0/1 knapsack over sums; iterate sums DOWNWARD so each number is used at most once.
- **Edge Cases:** Odd total → false; a single element larger than half → false; one element → false.
- **Complexity:** Time O(n · S/2) · Aux space O(S/2).
