import java.util.HashMap;
import java.util.Map;

public class HighestProfit {

	public static void main(String[] args) {

		System.out.println(supplierInventory(3, new long[] { 5L, 5L, 2L }, 7L));

	}

//https://aonecode.com/maximize-profit
	public static long supplierInventory(int numSupplier, long[] inventory, long order) {
		Map<Long, Long> map = new HashMap<>();
		long highest = 0;
		long profit = 0;
		for (int i = 0; i < inventory.length; i++) {
			map.put(inventory[i], map.getOrDefault(inventory[i], 0L) + 1);
			if (highest < inventory[i])
				highest = inventory[i];
		}

		System.out.println(map);

		while (order > 0 && !map.isEmpty()) {
			long highestFreq = map.get(highest);
			if (order > highestFreq) {
				profit += highestFreq * highest;
				order -= highestFreq;
				map.remove(highest);
				if (map.containsKey(highest - 1)) {
					long curr = map.get(highest - 1);
					map.put(highest - 1, map.get(highest - 1) + highestFreq);
				} else
					map.put(highest - 1, highestFreq);

				highest--;
			} else {
				profit += highest * order;
				order = 0;
			}
		}

		return profit;
	}

}
