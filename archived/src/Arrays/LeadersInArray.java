package Arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeadersInArray {

	public static void main(String[] args) {

		/****
		 * An element is leader if it is greater than all the elements to its right
		 * side. And the rightmost element is always a leader.
		 *****/

		System.out.println(getLeaders(new int[] { 16, 17, 4, 3, 5, 2 }));

	}

	public static List<Integer> getLeaders(int[] a) {

		List<Integer> res = new ArrayList<Integer>();
		int n = a.length;
		int maxTillNow = a[n - 1];
		res.add(maxTillNow);
		for (int i = n - 2; i >= 0; i--) {
			if (a[i] >= maxTillNow) {
				maxTillNow = a[i];
				res.add(maxTillNow);
			}
		}

		Collections.reverse(res);

		return res;

	}

}
