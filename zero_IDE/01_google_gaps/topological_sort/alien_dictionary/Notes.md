# Alien Dictionary — Hard

### 1. Invariant Trigger
* **Signal:** Sorted dictionary of words with unknown alphabet ordering -> Derive character precedence DAG from first differing characters.
* **Core Invariant:** For adjacent words $w_1$ and $w_2$, the earliest character index $j$ where $w_1[j] \neq w_2[j]$ defines a directed edge $w_1[j] \to w_2[j]$. All later characters convey zero ordering information.

### 2. Failure Traps & Edge Cases
* Prefix trap: If $w_1$ starts with $w_2$ and $|w_1| > |w_2|$ (e.g. `["apple", "app"]`), the list is invalid lexicographically; immediately return `""`.
* Cycle detection: If cycle exists in character graph, return `""`.
* Characters present in words but with no ordering constraints must still be present in the output alphabet.

### 3. Complexity & Variations
* **Time:** $O(C)$ where $C$ is total number of characters across all words in input.
* **Space:** $O(U + \min(U^2, N))$ where $U \le 26$ is unique alien alphabet characters and $N$ is word count.
* **Google Follow-up:** "What if the dictionary stream is unbounded?" -> Incrementally construct topological graph in an online stream and maintain reachability matrix using bitsets.
