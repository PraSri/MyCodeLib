import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TopKFrequentlyMentionedKeywords {

	public static void main(String[] args) {
		int k = 2;
		String[] keywords = { "anacell", "cetracular", "betacellular" };
		String[] reviews = { "Anacell provides the best services in the city", "betacellular has awesome services",
				"Best services provided by anacell, everyone should use anacell", };
		List l = Arrays.asList(getMostFrequentCommonwords(reviews, keywords, k));
		System.out.println(l);
	}

	public static String[] getMostFrequentCommonwords(String[] reviews, String[] keywords, int k) {

		Map<String, Integer> frequencies = new HashMap<>();

		List<String> keywordList = Arrays.asList(keywords);

		for (String review : reviews) {

			review = review.toLowerCase();

			Set<String> encounteredWords = new HashSet<>();

			for (final String reviewWord : review.split(" ")) {
				if (!encounteredWords.contains(reviewWord) && keywordList.contains(reviewWord)) {
					Integer currentFrequency = frequencies.getOrDefault(reviewWord, 0);
					frequencies.put(reviewWord, currentFrequency + 1);
					encounteredWords.add(reviewWord);
				}
			}
		}

		System.out.println(frequencies);

		String[] frequencyArray = frequencies.keySet().toArray(new String[k]);

		System.out.println(Arrays.asList(frequencyArray));

		Arrays.sort(frequencyArray, (a, b) -> frequencies.get(a).equals(frequencies.get(b)) ? a.compareTo(b)
				: frequencies.get(b) - frequencies.get(a));

		return Arrays.copyOfRange(frequencyArray, 0, k);
	}
}
