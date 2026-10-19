# Path Sum II — Medium

### 1. Invariant Trigger
* **Signal:** Find all root-to-leaf paths in a binary tree where values sum to target (Roku problem) -> Pre-order DFS with list backtracking.
* **Core Invariant:** A valid path must terminate strictly at a leaf node (`left == null && right == null`) with remaining sum equal to `leaf.val`. Backtracking guarantees clean path reuse.

### 2. Failure Traps & Edge Cases
* Non-leaf early termination: Stopping at an internal node when remaining sum reaches zero violates the "root-to-leaf" contract.
* Negative values: Values can be negative, so paths cannot be pruned early when sum exceeds target.
* Snapshot cloning: `results.add(new ArrayList<>(currentPath))` is mandatory; adding `currentPath` directly records empty lists after backtracking.

### 3. Complexity & Variations
* **Time:** $O(N^2)$ in worst-case (skewed tree where every leaf matches target and path copying takes $O(N)$).
* **Space:** $O(H)$ recursion stack where $H$ is tree height.
* **Google Follow-up:** "What if paths can start and end at ANY node, not just root-to-leaf?" -> Path Sum III using prefix sum hash map during DFS.
