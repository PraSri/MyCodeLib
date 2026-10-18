# Design Twitter

[LeetCode](https://leetcode.com/problems/design-twitter/) · Medium · Pattern: `k_way_merge`

- **Trigger Invariant:** Each followee's tweet list is already sorted by time, so the feed is a k-way merge of those lists' tails — stop after 10 pops instead of merging everything.
- **Edge Cases:** Following yourself (always include self, never store it); unfollow of a non-followed user; fewer than 10 tweets total.
- **Complexity:** Time post/follow O(1), feed O(F + 10 log F) for F followees · Aux space O(users + tweets).
