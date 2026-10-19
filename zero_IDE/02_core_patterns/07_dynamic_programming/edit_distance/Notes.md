# Edit Distance — Medium

### 1. Invariant Trigger
* **Signal:** Minimum operations (insert, delete, replace) to convert word1 to word2 -> Levenshtein Distance 2D DP.
* **Core Invariant:** If characters match, $dp[i][j] = dp[i-1][j-1]$. If they differ, choose the minimum cost among:
  - Replace: $dp[i-1][j-1] + 1$
  - Delete: $dp[i-1][j] + 1$
  - Insert: $dp[i][j-1] + 1$

### 2. Failure Traps & Edge Cases
* Empty string: If $word1$ is empty, answer is $|word2|$ (insertions). If $word2$ is empty, answer is $|word1|$ (deletions).
* 1D space optimization: Can be optimized to $O(\min(M, N))$ space using two rows.
* Off-by-one indexing: `word.charAt(i - 1)` maps to `dp[i]`.

### 3. Complexity & Variations
* **Time:** $O(M \cdot N)$ state evaluations.
* **Space:** $O(M \cdot N)$ or $O(N)$ with rolling row array.
* **Google Follow-up:** "What if different operations have arbitrary costs (e.g., delete=2, insert=3, replace=1)?" -> Standard weighted shortest path on grid DAG (Dijkstra if negative weights absent).
