# Palindrome Partitioning

[LeetCode](https://leetcode.com/problems/palindrome-partitioning/) · Medium · Pattern: `backtracking`

- **Trigger Invariant:** Choose the next cut end j only if s[start..j] is a palindrome; precomputing pal[i][j] (pal[i+1][j−1] && s[i]==s[j]) makes each check O(1).
- **Edge Cases:** Single char → [[c]]; all same chars (max number of partitions); string of distinct chars → one partition of singletons.
- **Complexity:** Time O(n · 2^n) · Aux space O(n²) table.
