package QuickHelper;

import java.util.LinkedHashMap;
import java.util.Map;

public class CharFrequencyMapInString {
	public static Map<Character, Integer> getFrequencyMap(String s) {
		
		Map<Character, Integer> m = new LinkedHashMap<Character, Integer>();

		for (char c : s.toCharArray()) {
			if (!m.containsKey(c)) {
				m.put(c, 1);
			} else {
				m.put(c, m.get(c) + 1);
			}
		}
		return m;
	}
}
