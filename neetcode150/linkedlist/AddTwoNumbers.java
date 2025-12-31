package linkedlist;

/**
 * 1. **Multiply Strings**
 * [https://leetcode.com/problems/multiply-strings/](https://leetcode.com/problems/multiply-strings/)
 * <p>
 * 2. **Add Binary**
 * [https://leetcode.com/problems/add-binary/](https://leetcode.com/problems/add-binary/)
 * <p>
 * 3. **Sum of Two Integers**
 * [https://leetcode.com/problems/sum-of-two-integers/](https://leetcode.com/problems/sum-of-two-integers/)
 * <p>
 * 4. **Add Strings**
 * [https://leetcode.com/problems/add-strings/](https://leetcode.com/problems/add-strings/)
 * <p>
 * 5. **Add Two Numbers II**
 * [https://leetcode.com/problems/add-two-numbers-ii/](https://leetcode.com/problems/add-two-numbers-ii/)
 * <p>
 * 6. **Add to Array-Form of Integer**
 * [https://leetcode.com/problems/add-to-array-form-of-integer/](https://leetcode.com/problems/add-to-array-form-of-integer/)
 * <p>
 * 7. **Add Two Polynomials Represented as Linked Lists**
 * [https://leetcode.com/problems/add-two-polynomials-represented-as-linked-lists/](https://leetcode.com/problems/add-two-polynomials-represented-as-linked-lists/)
 * <p>
 * 8. **Double a Number Represented as a Linked List**
 * [https://leetcode.com/problems/double-a-number-represented-as-a-linked-list/](https://leetcode.com/problems/double-a-number-represented-as-a-linked-list/)
 */

public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int v1 = (l1 != null) ? l1.val : 0;
            int v2 = (l2 != null) ? l2.val : 0;
            int val = v1 + v2 + carry;
            carry = val / 10;
            val = val % 10;
            curr.next = new ListNode(val);
            curr = curr.next;
            l1 = (l1 != null) ? l1.next : null;
            l2 = (l2 != null) ? l2.next : null;
        }
        return dummy.next;
    }
    /**
     * <a href="https://leetcode.com/problems/multiply-strings/">LeetCode - Multiply Strings</a>
     */
    public static class MultiplyStrings {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/add-binary/">LeetCode - Add Binary</a>
     */
    public static class AddBinary {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/sum-of-two-integers/">LeetCode - Sum of Two Integers</a>
     */
    public static class SumOfTwoIntegers {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/add-strings/">LeetCode - Add Strings</a>
     */
    public static class AddStrings {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/add-two-numbers-ii/">LeetCode - Add Two Numbers II</a>
     */
    public static class AddTwoNumbersIi {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/add-to-array-form-of-integer/">LeetCode - Add to Array-Form of Integer</a>
     */
    public static class AddToArrayFormOfInteger {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/add-two-polynomials-represented-as-linked-lists/">LeetCode - Add Two Polynomials Represented as Linked Lists</a>
     */
    public static class AddTwoPolynomialsRepresentedAsLinkedLists {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/double-a-number-represented-as-a-linked-list/">LeetCode - Double a Number Represented as a Linked List</a>
     */
    public static class DoubleANumberRepresentedAsALinkedList {
        // placeholder
    }

}
