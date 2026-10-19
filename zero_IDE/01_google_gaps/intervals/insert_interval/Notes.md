# Insert Interval — Medium

### 1. Invariant Trigger
* **Signal:** Inserting an interval into an already sorted, non-overlapping list of intervals -> 3-Phase linear sweep without resorting.
* **Core Invariant:** The interval space partitions into 3 disjoint subsets: (1) strictly left of `newInterval`, (2) overlapping with `newInterval`, (3) strictly right of `newInterval`.

### 2. Failure Traps & Edge Cases
* Empty list: `intervals = []`, must return `[newInterval]`.
* Non-overlapping boundary placement: `newInterval` placed before the very first or after the very last existing interval.
* Complete absorption: `newInterval` completely spans multiple existing intervals.

### 3. Complexity & Variations
* **Time:** $O(N)$ single pass over existing intervals.
* **Space:** $O(N)$ to build result list without mutating input.
* **Google Follow-up:** "Can we optimize the search for overlap boundaries to $O(\log N)$?" -> Use binary search for insertion points, then shift or copy slice in $O(N)$.
