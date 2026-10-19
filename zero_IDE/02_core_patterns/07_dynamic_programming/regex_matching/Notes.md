# Regular Expression Matching — Hard

### 1. Invariant Trigger
* **Signal:** Pattern matching supporting `.` (any single char) and `*` (zero or more of preceding char) (Microsoft R1 problem) -> 2D Dynamic Programming.
* **Core Invariant:** If $p[j-1] == \text{'*'}$:
  - Match zero times: $dp[i][j] = dp[i][j-2]$.
  - Match one or more times: if $p[j-2]$ matches $s[i-1]$, $dp[i][j] = dp[i-1][j]$.

### 2. Failure Traps & Edge Cases
* Base case for empty string: Empty $s$ can match $p$ with zero-occurrences like `a*b*`. Initializing `dp[0][j]` is critical.
* Leading `*` error: Problem statement guarantees valid patterns where `*` is preceded by a valid char.
* Index offset: Strings are 0-indexed, while DP table is 1-indexed.

### 3. Complexity & Variations
* **Time:** $O(M \cdot N)$ filling 2D table of size $(M+1) \times (N+1)$.
* **Space:** $O(M \cdot N)$ or $O(N)$ with row rolling.
* **Google Follow-up:** "How does this compare to Wildcard Matching (`*` matches any sequence)?" -> Wildcard matching does not require preceding character lookback, leading to $dp[i][j] = dp[i-1][j] \lor dp[i][j-1]$.
