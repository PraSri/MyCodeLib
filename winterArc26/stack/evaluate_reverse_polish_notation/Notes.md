# Evaluate Reverse Polish Notation

[LeetCode](https://leetcode.com/problems/evaluate-reverse-polish-notation/) · Medium · Pattern: `stack`

- **Trigger Invariant:** Postfix means every operator applies to the two most recent unconsumed values — exactly a stack; pop right operand first.
- **Edge Cases:** Operand order for '-' and '/'; division truncates toward zero (Java int division does); negative number tokens like "-11" are operands, not operators.
- **Complexity:** Time O(n) · Aux space O(n).
