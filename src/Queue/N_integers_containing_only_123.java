package Queue;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class N_integers_containing_only_123 {

	public static void main(String[] args) {
		
		System.out.println(solve(7));

	}

	public static ArrayList<Integer> solve(int A) {

		ArrayList<Integer> a = new ArrayList<>();

		a.add(1);

		if (A == 1) {
			return a;
		}

		a.add(2);

		if (A == 2) {
			return a;
		}

		a.add(3);

		if (A == 3) {
			return a;
		}

		int n = A - 3;

		Deque<Integer> d = new LinkedList<>();

		d.addAll(a);

		while (!d.isEmpty() && n > 0) {
			int x = 0;
			if(!d.isEmpty()) {
				x = d.getFirst();
			}
			int num = x;
			d.add(num*10+1);
			a.add(num*10+1);
			n--;
			if(n==0) {
				break;
			}
			d.add(num*10+2);
			a.add(num*10+2);
			n--;
			if(n==0) {
				break;
			}
			d.add(num*10+3);
			a.add(num*10+3);
			n--;
			if(n==0) {
				break;
			}
			d.removeFirst();
			
		}

		return a;

	}

}
