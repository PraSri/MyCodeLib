# Meeting Rooms II — Medium

### 1. Invariant Trigger
* **Signal:** Find minimum concurrent resources needed to schedule overlapping time intervals -> Two-pointer sweep over sorted starts and ends.
* **Core Invariant:** Whenever `start[i] < end[endPtr]`, an ongoing meeting has not finished before the next begins, requiring a new room. Otherwise, a room freed up and can be reused.

### 2. Failure Traps & Edge Cases
* Boundary overlap: A meeting ending at time 5 and another starting at time 5 do NOT conflict (`[1, 5]` and `[5, 10]`). The condition is strictly `starts[i] < ends[endPtr]`.
* Comparator overflow if using PriorityQueue: `(a, b) -> a - b` risks 32-bit overflow. Use `Integer.compare()`.
* Single meeting: Check $n = 1$ returns 1 room.

### 3. Complexity & Variations
* **Time:** $O(N \log N)$ for sorting start and end arrays.
* **Space:** $O(N)$ for start and end timestamp arrays.
* **Google Follow-up:** "What if intervals arrive in an unbounded stream?" -> Maintain active meeting end times in a Min-Heap (PriorityQueue) in $O(\log K)$ per update.
