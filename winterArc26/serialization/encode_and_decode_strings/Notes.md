# Encode and Decode Strings

[LeetCode](https://leetcode.com/problems/encode-and-decode-strings/) · Medium · Pattern: `serialization`

- **Trigger Invariant:** Length-prefix framing (`len#payload`): the decoder reads digits up to the first '#', then jumps exactly len chars, so payloads may contain any character including '#'.
- **Edge Cases:** Empty list vs list with one empty string ("0#"); strings containing '#' or digits; very long strings (multi-digit lengths).
- **Complexity:** Time O(total chars) · Aux space O(1) beyond the output.
