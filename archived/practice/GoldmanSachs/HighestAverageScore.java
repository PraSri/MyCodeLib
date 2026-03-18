package GoldmanSachs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HighestAverageScore {

	public static int hishestAverage(String[][] scores) {

		if (scores == null || scores.length == 0) {
			return -1;
		}

		int highestAve = 0;
		// map of student to list of marks
		Map<String, List<Integer>> map = new HashMap<>();

		for (int i = 0; i < scores.length; i++) {
			List<Integer> scoreList = map.get(scores[i][0]);
			if (scoreList == null) {
				List<Integer> currentScore = new ArrayList<>();
				currentScore.add(Integer.valueOf(scores[i][1]));
				map.put(scores[i][0], currentScore);
			} else {
				scoreList.add(Integer.valueOf(scores[i][1]));
				map.put(scores[i][0], scoreList);
			}
		}

		System.out.println(map);

		// go through the map. find the largest ave
		for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
			int currentAveScore = aveCalculate(entry.getValue());

			highestAve = Math.max(highestAve, currentAveScore);
		}

		return highestAve;
	}

	private static int aveCalculate(List<Integer> scores) {
		int len = scores.size();
		int sum = 0;
		for (int score : scores) {
			sum += score;
		}

		float ave = sum / len;
		return (int) ave;
	}

	public static void main(String[] args) {

		String[][] s = { { "Bob", "87" }, { "Mike", "35" }, { "Bob", "52" }, { "Jason", "35" }, { "Mike", "55" },
				{ "Jessica", "99" } };
		System.out.println(hishestAverage(s));
	}

}
