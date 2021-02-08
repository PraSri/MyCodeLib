import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class SearchSuggestionsSystem {

	public static void main(String[] args) {
		System.out.println(
				suggestedProducts(new String[] { "mobile", "mouse", "moneypot", "monitor", "mousepad" }, "mouse"));
	}

	public static List<List<String>> suggestedProducts(String[] products, String searchWord) {
		List<List<String>> res = new ArrayList<>();
		TreeMap<String, Integer> map = new TreeMap<>();
		Arrays.sort(products);
		List<String> productsList = Arrays.asList(products);

		for (int i = 0; i < products.length; i++) {
			map.put(products[i], i);
		}

		System.out.println(map);

		String key = "";
		for (char c : searchWord.toCharArray()) {
			key += c;
			String ceiling = map.ceilingKey(key);
			String floor = map.floorKey(key + "~");
			System.out.println("c=" + c + " ceiling=" + ceiling + " floor=" + floor);
			if (ceiling == null || floor == null)
				break;
			System.out.println(
					productsList.subList(map.get(ceiling), Math.min(map.get(ceiling) + 3, map.get(floor) + 1)));
			res.add(productsList.subList(map.get(ceiling), Math.min(map.get(ceiling) + 3, map.get(floor) + 1)));
		}

		while (res.size() < searchWord.length())
			res.add(new ArrayList<>());
		return res;
	}
}
