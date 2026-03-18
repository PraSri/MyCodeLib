import java.util.Collections;
import java.util.PriorityQueue;

public class ConstructTargetArrayWithMultipleSums {

	// Ques :
	// https://leetcode.com/problems/construct-target-array-with-multiple-sums/
	public static void main(String[] args) {

	}

	// giving wrong answer
	public static boolean isPossible(int[] A) {

		// max heap
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
		int sum = 0;
		for (int i : A) {
			sum += i;
		}

		while (true) {

			int x = pq.poll();
			sum -= x;
			if (x == 1 || sum == 1) {
				return true;
			}

			if (x < sum || sum == 0 || x % sum == 0) {

				return false;

			}

			x = x % sum;
			sum += x;
			pq.add(x);

		}

	}

}
