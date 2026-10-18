# Word Ladder

[LeetCode](https://leetcode.com/problems/word-ladder/) · Hard · Pattern: `graph_bfs`

- **Trigger Invariant:** Words are nodes, one-letter edits are unweighted edges, so BFS levels give the shortest transformation length; removing a word from the dictionary when enqueued marks it visited.
- **Edge Cases:** endWord not in wordList → 0; beginWord == endWord; beginWord need not be in the list; count is number of WORDS (start at 1).
- **Complexity:** Time O(N · L · 26) · Aux space O(N · L).
