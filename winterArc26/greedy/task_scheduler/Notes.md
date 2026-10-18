# Task Scheduler

[LeetCode](https://leetcode.com/problems/task-scheduler/) · Medium · Pattern: `greedy`

- **Trigger Invariant:** The most frequent task forces (maxFreq − 1) gaps of length n + 1, plus one final slot per task tied at maxFreq; if other tasks overflow the gaps, no idle is needed and the answer is just the task count.
- **Edge Cases:** n = 0 → tasks.length; several tasks tied at maxFreq; many distinct tasks (answer = tasks.length).
- **Complexity:** Time O(T) · Aux space O(26).
