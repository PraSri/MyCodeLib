# Daily Temperatures — Medium

### 1. Invariant Trigger
* **Signal:** For each day, find how many days until a warmer temperature occurs -> Next Greater Element pattern with monotonic stack.
* **Core Invariant:** Stack stores indices with strictly decreasing temperatures. When day $i$ has temperature $> temp[stack.peek()]$, day $i$ is the nearest warmer day for the day at stack top.

### 2. Failure Traps & Edge Cases
* Strict vs non-strict inequality: Problem specifies a *warmer* day, so condition is strictly `temperatures[stack.peek()] < temperatures[i]`. Equal temperature days must stay on stack.
* Days with no warmer future day: Remain 0 in the result array by default.
* Single-element array: returns `[0]`.

### 3. Complexity & Variations
* **Time:** $O(N)$ linear time since each day index is pushed and popped once.
* **Space:** $O(N)$ auxiliary space for stack.
* **Google Follow-up:** "How to solve this in $O(1)$ auxiliary space?" -> Traverse backwards and use the already-computed `result` array as jump pointers.
