package LinkedList;

public class AddTwoNumbersWithEachDigitInNode {

// https://leetcode.com/problems/add-two-numbers/
    public static void main(String[] args) {
        // Prints "Hello, World" to the terminal window.
        System.out.println("Hello, World");
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode c1 = l1;
        ListNode c2 = l2;
        ListNode res = new ListNode(0);
        ListNode l = res;
        int sum = 0;
        while(c1!=null || c2!=null) {
            sum /= 10;
            if(c1!=null){
                sum += c1.val;
                c1 = c1.next;
            }
            if(c2!=null){
                sum += c2.val;
                c2 = c2.next
            }
            l.next = new ListNode(sum%10);
            l = l.next;
        } 
        if(sum/10 != 0){
            l.next = new ListNode(1);
        }       
        return res.next;
    }

}
