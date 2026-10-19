# Reverse Bits — Easy

### 1. Invariant Trigger
* **Signal:** Reverse the binary bits of a 32-bit integer -> Bitwise shift and mask over all 32 positions.
* **Core Invariant:** Extract least significant bit of input with `n & 1`, shift accumulated result left by 1, and logically shift input right by 1 using `>>>=`.

### 2. Failure Traps & Edge Cases
* Arithmetic vs Logical shift: Using arithmetic right shift `>>` sign-extends negative integers, polluting the MSB with 1s. You MUST use logical right shift `>>>`.
* Premature loop termination: `while (n != 0)` fails when reversing numbers with trailing zeros (e.g. 1 reversed is $2^{31}$). The loop MUST run strictly 32 iterations.

### 3. Complexity & Variations
* **Time:** $O(1)$ fixed 32 iterations.
* **Space:** $O(1)$ auxiliary memory.
* **Google Follow-up:** "How would you optimize reverseBits if called billions of times?" -> Divide-and-conquer bit swapping (swap adjacent bits, then 2-bit chunks, nibbles, bytes) or 8-bit lookup table.
