# Copy List with Random Pointer

[LeetCode](https://leetcode.com/problems/copy-list-with-random-pointer/) · Medium · Pattern: `linked_list`

- **Trigger Invariant:** Interleave each copy right after its original (A→A'→B→B'); then original.random.next IS the copy's random target, replacing the old→new HashMap.
- **Edge Cases:** Empty list; random == null; random pointing to itself; must fully restore the original list when splitting.
- **Complexity:** Time O(n) · Aux space O(1) besides the copy (HashMap variant is O(n)).
