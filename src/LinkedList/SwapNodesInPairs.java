package LinkedList;

public class SwapNodesInPairs {

  public ListNode swapPairs(ListNode head) {
        if(head==null || head.next==null){
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode curr = dummy.next;
        
        while(curr!=null && curr.next!=null) {
            
            prev.next = curr.next;
            curr.next = prev.next.next;
            prev.next.next = curr;
            prev = curr;
            curr = curr.next;
        }
        
        return dummy.next;
        
    }

}
