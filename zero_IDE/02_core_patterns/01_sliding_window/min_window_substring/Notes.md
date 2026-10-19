# Minimum Window Substring — Hard

### 1. Invariant Trigger
* **Signal:** Find smallest contiguous substring containing all characters of pattern $t$ with frequencies -> Two-pointer sliding window with frequency array.
* **Core Invariant:** Expand `r` to achieve feasibility (`formed == required`), then contract `l` greedily to achieve minimality while preserving feasibility.

### 2. Failure Traps & Edge Cases
* Exact frequency matching: `formed` counter increments ONLY when `windowCounts[c] == targetCounts[c]`. Surplus occurrences must not re-increment `formed`.
* Target string longer than source string: `s.length() < t.length()`, immediately return `""`.
* ASCII space: use 128-sized array to accommodate upper and lower case letters without map boxing overhead.

### 3. Complexity & Variations
* **Time:** $O(|S| + |T|)$ since each pointer advances at most $|S|$ times.
* **Space:** $O(1)$ fixed 128-element frequency arrays.
* **Google Follow-up:** "What if $|S|$ is streamed from network and does not fit in RAM?" -> Keep a rolling buffer of size $k$ containing character positions of matching target characters only.
