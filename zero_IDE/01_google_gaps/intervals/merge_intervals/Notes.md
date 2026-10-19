# Merge Intervals — Medium

### 1. Invariant Trigger
* **Signal:** Collection of contiguous ranges that may overlap, requiring unification -> Sort by interval start coordinate.
* **Core Invariant:** After sorting by `start`, any interval that can overlap with `current` must satisfy `next.start <= current.end`. If satisfied, merge by `current.end = max(current.end, next.end)`.

### 2. Failure Traps & Edge Cases
* Comparator subtraction `a[0] - b[0]` overflows for negative values (`Integer.MIN_VALUE`). Always use `Integer.compare(a[0], b[0])`.
* Fully nested intervals: `[1, 10]` and `[2, 5]` -> `current.end` must take `Math.max(10, 5) = 10`, not overwrite with $5$.
* Point intervals: `[1, 4]` and `[4, 5]` overlap at point 4 and must be merged to `[1, 5]`.

### 3. Complexity & Variations
* **Time:** $O(N \log N)$ dominated by interval sorting.
* **Space:** $O(N)$ for sorting recursion and merged output list.
* **Google Follow-up:** "How to merge intervals arriving continuously from a real-time data stream?" -> Interval Tree or Segment Tree supporting $O(\log N)$ dynamic insertions.
