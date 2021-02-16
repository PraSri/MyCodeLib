package Easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class KthNonRepeatingCharacter {

	public static class Counter {
		public int count, index;

		public Counter(int count, int index) {
			this.count = count;
			this.index = index;
		}
	}

	public static void main(String[] args) {
		System.out.println(getKthNonRepChar("geeksforgeeks", 3));
	}

	public static char getKthNonRepChar(String s, int k) {

		Map<Character, Counter> m = new HashMap<Character, Counter>();
		int i = 0;
		for (char c : s.toCharArray()) {
			if (!m.containsKey(c)) {
				Counter counter = new Counter(1, i);
				m.put(c, counter);
			} else {
				Counter prevCounter = m.get(c);
				int newCount = prevCounter.count + 1;
				Counter currCounter = new Counter(newCount, i);
				m.put(c, currCounter);
			}
			i++;
		}

		ArrayList<Integer> a = new ArrayList<Integer>();

		for (Map.Entry<Character, Counter> me : m.entrySet()) {

			int index = me.getValue().index;
			int count = me.getValue().count;
			if (count == 1) {
				a.add(index);
			}

		}

		Collections.sort(a);

		return a.size() < k ? '$' : s.charAt(a.get(k - 1));

	}

}
