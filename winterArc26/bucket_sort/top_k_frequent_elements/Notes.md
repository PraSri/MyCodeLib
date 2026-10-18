# Top K Frequent Elements

[LeetCode](https://leetcode.com/problems/top-k-frequent-elements/) · Medium · Pattern: `bucket_sort`

- **Trigger Invariant:** Frequency is bounded by n, so bucket index = frequency gives a linear-time 'sort by count'; scan buckets from high to low until k collected.
- **Edge Cases:** k == number of distinct values; all elements equal; negative values (map keys, no indexing by value).
- **Complexity:** Time O(n) · Aux space O(n).
