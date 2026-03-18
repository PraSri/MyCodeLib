import java.util.Arrays;
import java.util.HashMap;

public class NearestCity {

	public static void main(String[] args) {
		System.out.println("Hello World");
		int noOfQueries = 3;
		int noOfcities = 3;
		int[] x = new int[] { 3, 2, 1 };
		int[] y = new int[] { 3, 2, 3 };
		String[] cities = new String[] { "c1", "c2", "c3" };
		String[] queries = new String[] { "c1", "c2", "c3" };
		String[] result = findNearestCities(noOfcities, cities, x, y, noOfQueries, queries);

		for (String s : result) {
			System.out.print(s + ", ");
		}
	}

	public static class City {
		int x;
		int y;
		String name;
		City nearestCityX;
		City nearestCityY;
		City nearestCity;

		public City(String name, int x, int y) {
			this.x = x;
			this.y = y;
			this.name = name;
		}

		@Override
		public String toString() {
			return "City [x=" + x + ", y=" + y + ", name=" + name + "]";
		}

	}

	public static String[] findNearestCities(int numOfCities, String[] cities, int[] xCoordinates, int[] yCoordinates,
			int numOfQueries, String[] queries) {

		City[] allCities = new City[numOfCities];

		HashMap<String, City> map = new HashMap<>();

		for (int i = 0; i < numOfCities; i++) {
			allCities[i] = new City(cities[i], xCoordinates[i], yCoordinates[i]);
			map.put(allCities[i].name, allCities[i]);
		}

		System.out.println(map);

//		Arrays.sort(allCities, (a, b) -> a.y - b.y);
		System.out.println(Arrays.asList(allCities));

		Arrays.sort(allCities, (a, b) -> a.x - b.x);
		System.out.println(Arrays.asList(allCities));
		
		int j;

		for (int i = 0; i < numOfCities;) {

			for (j = i + 1; j < numOfCities; j++) {
				if (allCities[j].x != allCities[i].x) {
					break;
				}
			}

			if (i + 1 < j) {
				allCities[i].nearestCityY = allCities[i + 1];
				allCities[j - 1].nearestCityY = allCities[j - 2];
			}

			for (int k = i + 1; k < j - 1; k++) {
				allCities[k].nearestCityY = allCities[k - 1];
				City p = allCities[k + 1];
				if (p.y - allCities[k].y < allCities[k].y - allCities[k].nearestCityY.y) {
					allCities[k].nearestCityY = p;
				} else if (p.y - allCities[k].y > allCities[k].y - allCities[k].nearestCityY.y) {
					// DO NOTHING
				} else if (p.name.compareTo(allCities[k].nearestCityY.name) < 0) {
					allCities[k].nearestCityY = p;
				}
			}

			i = j;
		}

		Arrays.sort(allCities, (a, b) -> a.y - b.y);

		for (int i = 0; i < numOfCities;) {

			for (j = i + 1; j < numOfCities; j++) {
				if (allCities[j].y != allCities[i].y) {
					break;
				}
			}

			if (i + 1 < j) {
				allCities[i].nearestCityX = allCities[i + 1];
				allCities[j - 1].nearestCityX = allCities[j - 2];
			}

			for (int k = i + 1; k < j - 1; k++) {
				allCities[k].nearestCityX = allCities[k - 1];
				City p = allCities[k + 1];
				if (p.x - allCities[k].x < allCities[k].x - allCities[k].nearestCityX.x) {
					allCities[k].nearestCityX = p;
				} else if (p.x - allCities[k].x > allCities[k].x - allCities[k].nearestCityX.x) {
					// DO NOTHING
				} else if (p.name.compareTo(allCities[k].nearestCityX.name) < 0) {
					allCities[k].nearestCityX = p;
				}
			}
			i = j;
		}

		for (City city : allCities) {
			city.nearestCity = city.nearestCityX;
			City p = city.nearestCityY;
			if (city.nearestCity == null) {
				city.nearestCity = p;
			} else if (p == null) {
				// DO NOTHING
			} else if (Math.abs(p.y - city.y) < Math.abs(city.x - city.nearestCity.x)) {
				city.nearestCity = p;
			} else if (Math.abs(p.y - city.y) > Math.abs(city.x - city.nearestCity.x)) {
				// DO NOTHING
			} else if (p.name.compareTo(city.nearestCity.name) < 0) {
				city.nearestCity = p;
			}
		}

		String[] result = new String[numOfQueries];

		for (int i = 0; i < numOfQueries; i++) {
			City temp = map.get(queries[i]).nearestCity;
			result[i] = temp == null ? "NONE" : temp.name;
		}

		return result;
	}

}
