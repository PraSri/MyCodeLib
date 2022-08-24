package a_linkedlist;

import LinkedList.ListNode;

public class AddTwoNumbers2 {

//    https://leetcode.com/problems/add-two-numbers-ii/

    // TODO : https://leetcode.com/problems/add-two-numbers-ii/discuss/687339/Java-O(N)-solution-with-follow-up-question-no-recursion-no-stacks

    public static void main(String[] args) {
        ListNode l1 = new ListNode(7);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        l1.next.next.next = new ListNode(3);

        System.out.println("l1");
        printLL(l1);
        System.out.println();

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        System.out.println("l2");
        printLL(l2);
        System.out.println();

        System.out.println("ans");
        ListNode ans = addTwoNumbers(l1, l2);
        printLL(ans);
    }

    private static void printLL(ListNode ans) {
        ListNode curr = ans;
        while (curr != null) {
            System.out.print(curr.val + " -> ");
            curr = curr.next;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        // sizes of l1, l2
        int s1 = 0, s2 = 0;

        for (ListNode curr = l1; curr != null; curr = curr.next) s1++;
        for (ListNode curr = l2; curr != null; curr = curr.next) s2++;

        // make longer list as first list
        if (s1 < s2) {
            int s = s1;
            s1 = s2;
            s2 = s;
            ListNode l = l1;
            l1 = l2;
            l2 = l;
        }

        ListNode result = null;

        // build temp result in reverse manner
        for (int i = 0; i < s1 - s2; i++) {
            result = new ListNode(l1.val, result);
            l1 = l1.next;
        }

        for (int i = 0; i < s2; i++) {
            result = new ListNode(l1.val + l2.val, result);
            l1 = l1.next;
            l2 = l2.next;
        }

        ListNode curr = result;
        result = null;
        int carry = 0;
        while (curr != null) {
            // normalize logic
            int sum = curr.val + carry;
            if (sum >= 10) {
                curr.val = sum % 10;
                carry = 1;
            } else {
                curr.val = sum;
                carry = 0;
            }
            // reverse logic
            ListNode temp = curr.next;
            curr.next = result;
            result = curr;
            curr = temp;
        }

        // extra carry need extra head with 1
        if (carry > 0) {
            result = new ListNode(carry, result);
        }
        return result;
    }

}
