package GoldmanSachs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAllWordsHavingSameCharAsInputString {

	public static List<String> getAnagrams(String[] words, String ip) {

		Map<String, String> m = new HashMap<>();
		List<String> res = new ArrayList<>();
		char[] charArray = ip.toCharArray();
		Arrays.sort(charArray);
		String sortedIp = String.valueOf(charArray);

		for (String s : words) {
			charArray = s.toCharArray();
			Arrays.sort(charArray);
			String sortedString = String.valueOf(charArray);
			m.put(s, sortedString);
		}

		for (Map.Entry<String, String> me : m.entrySet()) {
			if (!me.getKey().equals(ip) && me.getValue().equals(sortedIp)) {
				res.add(me.getKey());
			}
		}

		return res;

	}
}
