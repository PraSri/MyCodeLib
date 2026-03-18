package Stack;

public class MinStack_v3 {
    
  class Node{
        int value;
        int min;
        Node next;
        
        Node(int x, int min){
            this.value=x;
            this.min=min;
            next = null;
        }
    }
  
    Node head;
    public void push(int x) {
        if(null==head){
            head = new Node(x,x);
        }else{
            Node n = new Node(x, Math.min(x,head.min));
            n.next=head;
            head=n;
        }
    }

    public void pop() {
        if(head!=null)
            head =head.next;
    }

    public int top() {
        if(head!=null)
            return head.value;
        return -1;
    }

    public int getMin() {
        if(null!=head)
            return head.min;
        return -1;
    }
}
