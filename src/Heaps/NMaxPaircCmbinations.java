package Heaps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class NMaxPaircCmbinations {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public class Tuple {
		public int sum;
		public int i, j;

		public Tuple(int sum, int i, int j) {
			this.sum = sum;
			this.i = i;
			this.j = j;
		}

	}

	public ArrayList<Integer> solve(ArrayList<Integer> A, ArrayList<Integer> B) {

		ArrayList<Integer> res = new ArrayList<Integer>();
		Collections.sort(A);
		Collections.sort(B);
		int n = A.size();
		return maxPairSum(A, B, res, n);

	}

	private ArrayList<Integer> maxPairSum(ArrayList<Integer> a, ArrayList<Integer> b, ArrayList<Integer> res, int k) {

		int n = a.size();
		Comparator<Tuple> comparator = new Comparator<Tuple>() {
			@Override
			public int compare(Tuple t1, Tuple t2) {
				return t2.sum - t1.sum;
			}
		};

		PriorityQueue<Tuple> maxHeap = new PriorityQueue<Tuple>(comparator);

		Set<String> set = new HashSet<String>();

		Tuple largest = new Tuple(a.get(n - 1) + b.get(n - 1), n - 1, n - 1);
		maxHeap.add(largest);
		set.add(buildIndexString(n - 1, n - 1));

		for (int ct = 0; ct < k; ct++) {
			Tuple tuple = maxHeap.poll();
			res.add(tuple.sum);
			int i = tuple.i;
			int j = tuple.j;
			if (!set.contains(buildIndexString(i - 1, j))) {
				set.add(buildIndexString(i - 1, j));
				Tuple newTuple = new Tuple(a.get(i - 1) + b.get(j), i - 1, j);
				maxHeap.add(newTuple);
			}
			if (!set.contains(buildIndexString(i, j - 1))) {
				set.add(buildIndexString(i, j - 1));
				Tuple newTuple = new Tuple(a.get(i) + b.get(j - 1), i, j - 1);
				maxHeap.add(newTuple);
			}

		}

		return res;
	}

	private String buildIndexString(int i, int j) {
		return new StringBuilder().append(i).append('#').append(j).toString();
	}

}
