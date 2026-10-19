# Longest Repeating Character Replacement — Medium

### 1. Invariant Trigger
* **Signal:** Longest contiguous substring with identical characters obtainable by replacing at most $k$ characters -> Sliding window tracking max character frequency.
* **Core Invariant:** A window of length $W = r - l + 1$ is valid if and only if $W - \text{maxFreq} \le k$. Non-majority characters can all be converted using at most $k$ flips.

### 2. Failure Traps & Edge Cases
* Decrementing `maxFreq`: You do NOT need to decrement `maxFreq` when sliding `l` because a smaller `maxFreq` will never yield a longer valid window than what we've already found.
* All characters identical or $k \ge |S|$: entire string can be made uniform, answer is $|S|$.
* Single-character string: $n = 1$ returns 1.

### 3. Complexity & Variations
* **Time:** $O(N)$ where $N = s.\text{length}()$.
* **Space:** $O(1)$ fixed 26-element integer frequency array.
* **Google Follow-up:** "What if the alphabet includes all Unicode characters?" -> Use `HashMap<Character, Integer>` and update `maxFreq` lazily.
