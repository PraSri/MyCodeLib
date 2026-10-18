# Container With Most Water

[LeetCode](https://leetcode.com/problems/container-with-most-water/) · Medium · Pattern: `two_pointers`

- **Trigger Invariant:** Area is capped by the shorter wall; moving the taller wall inward can only shrink width without raising the cap, so always discard the shorter side.
- **Edge Cases:** Two elements; equal heights (move either); zero heights; width × height fits int under constraints.
- **Complexity:** Time O(n) · Aux space O(1).
