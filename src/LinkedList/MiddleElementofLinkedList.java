package LinkedList;

public class MiddleElementofLinkedList {

	public static void main(String[] args) {

	}

	public static int solve(ListNode A) {

		ListNode s, f;

		s = A;
		f = A.next;
		
		int c = 0;
		ListNode t = A;
		while(t!=null) {
			c++;
			t = t.next;
		}

		while (f != null && f.next != null) {
			s = s.next;
			f = f.next.next;
		}
		
		if(c%2==0) {
			s=s.next;
		}
		
		return s.val;
	}

}
