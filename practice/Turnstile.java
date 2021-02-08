import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Turnstile {

	public static void main(String[] args) {

		System.out.println(getTimes(List.of(0, 0, 1, 5), List.of(0, 1, 1, 0)));

	}

	public static class Pair {
		public int time, person;

		public Pair(int t, int p) {
			time = t;
			person = p;
		}
	}

	public static List<Integer> getTimes(List<Integer> time, List<Integer> dir) {

		int n = time.size();
		List<Integer> res = new ArrayList<Integer>(n);
		int[] ans = new int[n];

		Queue<Pair> entry = new LinkedList<Turnstile.Pair>();
		Queue<Pair> exit = new LinkedList<Turnstile.Pair>();

		for (int i = 0; i < n; i++) {
			Pair pair = new Pair(time.get(i), i);
			if (dir.get(i) == 1) {
				exit.add(pair);
			} else {
				entry.add(pair);
			}
		}

		int currTime = 0;
		int lock = -1;// entry is only allowed when last second was entry

		while (!entry.isEmpty() || !exit.isEmpty()) {

			if (isExitPossible(exit, entry, currTime, lock)) {

				ans[exit.peek().person] = currTime;
				exit.poll();
				lock = 1;

			} else if (isEntryPossible(entry, currTime)) {

				ans[entry.peek().person] = currTime;
				entry.poll();
				lock = 0;

			} else {
				lock = -1;
			}

			currTime++;

		}

		for (int i : ans)
			res.add(i);
		return res;

	}

	private static boolean isEntryPossible(Queue<Pair> entry, int currTime) {
		if ((!entry.isEmpty() && entry.peek().time <= currTime))
			return true;

		return false;
	}

	private static boolean isExitPossible(Queue<Pair> exit, Queue<Pair> entry, int currTime, int lock) {

		if ((!exit.isEmpty() && exit.peek().time <= currTime)
				&& ((lock == -1 || lock == 1) || (entry.isEmpty()) || (entry.peek().time > currTime)))
			return true;

		return false;
	}

}
