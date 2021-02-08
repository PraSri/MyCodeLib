import java.util.HashMap;
import java.util.Map;

public class PairsOfSongs {
//https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/
	public static void main(String[] args) {

		System.out.println(numPairsDivisibleBy60(new int[] { 30, 20, 150, 100, 40 }));

	}

	public static int numPairsDivisibleBy60(int[] time) {

		Map<Integer, Integer> m = new HashMap<>();

		int ans = 0;

		for (int t : time) {

			int other = (60 - t % 60) % 60;
			ans += m.getOrDefault(other, 0);
			m.put(t % 60, 1 + m.getOrDefault(t % 60, 0));
			System.out
					.println("t = " + t + " t%60 = " + t % 60 + " other = " + other + " map = " + m + " ans = " + ans);
		}

		return ans;

	}

}
