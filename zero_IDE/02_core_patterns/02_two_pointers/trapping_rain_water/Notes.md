# Trapping Rain Water — Hard

### 1. Invariant Trigger
* **Signal:** Calculate trapped water between elevation bars -> Two Pointers converging inward with `leftMax` and `rightMax`.
* **Core Invariant:** Water trapped at position $i$ is bounded by $\min(\text{leftMax}[i], \text{rightMax}[i]) - \text{height}[i]$. If $height[l] < height[r]$, then $leftMax$ is guaranteed to be the bottleneck, so water at $l$ is strictly determined by $leftMax - height[l]$.

### 2. Failure Traps & Edge Cases
* Monotonically increasing or decreasing elevations: No water can be trapped; returns 0.
* Flat plains: consecutive equal heights must be handled without double-counting.
* Auxiliary array overhead: $O(N)$ prefix/suffix max arrays work, but two pointers achieves $O(1)$ space (Tekion interview differentiator).

### 3. Complexity & Variations
* **Time:** $O(N)$ single pass with two pointers.
* **Space:** $O(1)$ auxiliary space.
* **Google Follow-up:** "How to solve Trapping Rain Water 2D (grid of elevations)?" -> Min-Heap PriorityQueue starting from grid perimeter, relaxing heights inwards (modified Dijkstra).
