package common;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class SortMapByValues<K, V> {

	public Map<K, V> getSortedByValuesMap(Map<K, V> map) {

		List<Map.Entry<K, V>> listOfEntrySet = new LinkedList<Map.Entry<K, V>>(map.entrySet());

		Comparator<Map.Entry<K, V>> comparator = new Comparator<Map.Entry<K, V>>() {

			@Override
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				MyObject<V> v1 = (MyObject) o1.getValue();
				MyObject<V> v2 = (MyObject) o2.getValue();

				return v1.compareTo(v2);

			}
		};

		Collections.sort(listOfEntrySet, comparator);

		Map<K, V> temp = new LinkedHashMap<K, V>();

		for (Map.Entry<K, V> me : listOfEntrySet) {
			temp.put(me.getKey(), me.getValue());
		}

		return temp;

	}

}
