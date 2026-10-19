# Word Search II — Hard

### 1. Invariant Trigger
* **Signal:** Search 2D board for multiple words simultaneously without restarting search per word -> Prefix Trie + DFS Backtracking with cell marking.
* **Core Invariant:** Simultaneous prefix pruning: DFS continues only if the board prefix exists in the Trie. Once a word is found, setting `node.word = null` prevents duplicate entries and prunes redundant branches.

### 2. Failure Traps & Edge Cases
* Duplicate words added to result: Without setting `node.word = null`, the same word found via a different path is added multiple times.
* Visiting the same cell twice: Use `#` in-place marking to avoid an auxiliary `visited[][]` matrix and memory allocations.
* Prefix trie memory: Store words in Trie nodes directly at end-of-word leaves to eliminate `StringBuilder` string construction during DFS.

### 3. Complexity & Variations
* **Time:** $O(M \cdot N \cdot 4^{L})$ where $L$ is max word length. Trie prefix matching prunes majority of paths early.
* **Space:** $O(\sum \text{len}(words))$ for the Trie + $O(L)$ recursion stack.
* **Google Follow-up:** "How to prune leaf nodes from Trie after matching?" -> Decrement child counts and unlink leaf nodes dynamically during DFS.
