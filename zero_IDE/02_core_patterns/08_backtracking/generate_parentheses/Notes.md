# Generate Parentheses — Medium

### 1. Invariant Trigger
* **Signal:** Generate all combinations of well-formed parentheses of length $2n$ -> Backtracking with balance counters.
* **Core Invariant:** A prefix is valid if and only if `close <= open <= n`. You can branch to `'('` when `open < n`, and branch to `')'` when `close < open`.

### 2. Failure Traps & Edge Cases
* Branching condition errors: Allowing `close < max` instead of `close < open` generates invalid expressions like `)(`.
* Backtracking mutation: Must delete appended char after recursion to preserve StringBuilder state.
* Base case: $n = 1$ returns `["()"]`.

### 3. Complexity & Variations
* **Time:** $O(\frac{4^n}{\sqrt{n}})$ bounded by the $n$-th Catalan number $C_n = \frac{1}{n+1}\binom{2n}{n}$.
* **Space:** $O(n)$ recursion stack and string builder buffer.
* **Google Follow-up:** "How to generate the k-th valid parenthesis combination without generating previous ones?" -> Combinatorial counting using Catalan numbers and binomial coefficients.
