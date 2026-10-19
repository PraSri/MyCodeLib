# Counting Bits — Easy

### 1. Invariant Trigger
* **Signal:** Count set bits for all numbers from $0$ to $n$ in linear time $O(n)$ without calling built-in popcount -> DP with bitwise transitions.
* **Core Invariant:** Brian Kernighan's identity `i & (i - 1)` clears the lowest set bit. Therefore, `countBits(i) = countBits(i & (i - 1)) + 1`.

### 2. Failure Traps & Edge Cases
* Array sizing: `n + 1` elements required because range is $[0, n]$ inclusive.
* Base case: `ans[0] = 0` initialized by default.
* Alternative transition `ans[i] = ans[i >> 1] + (i & 1)` is equally valid and cache-friendly.

### 3. Complexity & Variations
* **Time:** $O(N)$ exact single pass.
* **Space:** $O(1)$ auxiliary space beyond the return array of size $N + 1$.
* **Google Follow-up:** "How to count total set bits for all numbers from 1 to N in O(log N) time?" -> Digit DP / counting set bits per bit position across $2^k$ blocks.
