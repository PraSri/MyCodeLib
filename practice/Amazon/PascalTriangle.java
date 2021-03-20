package Amazon;

import java.util.ArrayList;

public class PascalTriangle {

	public static void main(String[] args) {
		
		
		System.out.println(solve(5));

	}

	public static ArrayList<ArrayList<Integer>> solve(int A) {
		ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
		ArrayList<Integer> ar;
		for (int i = 0; i < A; i++) {
			ar = new ArrayList<>();
			for (int j = 0; j <= i; j++) {
				if (j == 0 || j == i)
					ar.add(1);
				else
					ar.add(arr.get(i - 1).get(j - 1) + arr.get(i - 1).get(j));
			}
			arr.add(ar);
		}
		return arr;
	}

}
