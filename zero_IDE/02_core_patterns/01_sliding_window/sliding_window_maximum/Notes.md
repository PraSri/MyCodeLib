# Sliding Window Maximum — Hard

### 1. Invariant Trigger
* **Signal:** Maximum element in every sliding contiguous window of size $k$ in $O(N)$ total time -> Monotonic Deque of indices.
* **Core Invariant:** Deque maintains indices of candidates in strictly decreasing order of value. An incoming element invalidates all older candidates with values $\le nums[i]$ because they can never be the maximum of any future window containing $nums[i]$.

### 2. Failure Traps & Edge Cases
* Storing values instead of indices: storing values prevents verifying whether the maximum has expired outside the $k$-window boundary.
* Comparator / PriorityQueue TLE: $O(N \log k)$ or $O(N k)$ removal fails strict time limits (Agoda/Coupang failure pattern).
* Boundary: $k = 1$ returns exact copy of $nums$; $k = nums.length$ returns single max element.

### 3. Complexity & Variations
* **Time:** $O(N)$ because each element index is pushed and popped at most once.
* **Space:** $O(k)$ auxiliary space for deque storing at most $k$ indices.
* **Google Follow-up:** "How to implement Sliding Window Median in $O(\log k)$ per window?" -> Two balanced heaps (Max-Heap + Min-Heap) with lazy deletion via hash table.
