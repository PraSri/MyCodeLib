package GoldmanSachs;

//Java program to find minimum 
//number of steps needed to
//convert a number x into y 
//with two operations allowed : 
//(1) multiplication with 2 
//(2) subtraction with 1.

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

class GFG {
	
	int val;
	int steps;

	public GFG(int val, int steps) {
		this.val = val;
		this.steps = steps;
	}
}

public class Minimum_number_of_operation_required_to_convert_number_x_into_y {
	
	private static int minOperations(int src, int target) {

		Set<GFG> visited = new HashSet<>(1000);
		LinkedList<GFG> queue = new LinkedList<GFG>();

		GFG node = new GFG(src, 0);

		queue.offer(node);
		visited.add(node);

		while (!queue.isEmpty()) {
			GFG temp = queue.poll();
			visited.add(temp);

			if (temp.val == target) {
				return temp.steps;
			}

			int mul = temp.val * 2;
			int sub = temp.val - 1;

			// given constraints
			if (mul > 0 && mul < 1000) {
				GFG nodeMul = new GFG(mul, temp.steps + 1);
				queue.offer(nodeMul);
			}
			if (sub > 0 && sub < 1000) {
				GFG nodeSub = new GFG(sub, temp.steps + 1);
				queue.offer(nodeSub);
			}
		}
		
		return -1;
	}

	// Driver code
	public static void main(String[] args) {
		// int x = 2, y = 5;
		int x = 4, y = 7;
		GFG src = new GFG(x, y);
		System.out.println(minOperations(x, y));
	}
}

//This code is contributed by Rahul
