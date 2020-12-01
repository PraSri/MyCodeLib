package QuickHelper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FirstNonRepeatingCharInString {

	public static void main(String[] args) {

		System.out.println(getFirstNonRepChar_v2("geeksforgeeks"));

	}

	/***** Hashmap + two times string traversal *******/

	public static char getFirstNonRepChar(String s) {

		Map<Character, Integer> m = CharFrequencyMapInString.getFrequencyMap(s);

		Iterator<Map.Entry<Character, Integer>> it = m.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry<Character, Integer> me = it.next();
			if (me.getValue() == 1) {
				return me.getKey();
			}

		}

		return '$';

	}

	/******* Hashmap + single string traversal ***********************/

	public static class Counter {
		public int count, index;

		public Counter(int count, int index) {
			this.count = count;
			this.index = index;
		}
	}

	public static char getFirstNonRepChar_v2(String s) {

		Map<Character, Counter> m = new HashMap<Character, FirstNonRepeatingCharInString.Counter>();
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

		int res = Integer.MAX_VALUE;

		for (Map.Entry<Character, Counter> me : m.entrySet()) {

			int index = me.getValue().index;
			int count = me.getValue().count;
			if (count == 1 && index < res) {
				res = index;
			}

		}

		return res == Integer.MAX_VALUE ? '$' : s.charAt(res);

	}

}
