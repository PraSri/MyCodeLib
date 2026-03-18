package subsets;

import java.util.*;

public class LetterTilePossibilities {

    /**
     * Time complexity = O(nlogn) + O(2^n) + O(n*2^n) = O(n*2^n)
     * Space complexity = O(n) + O(2^n * n) + O(n) = O(2^n * n)
     */

    public static int numTilePossibilities(String tiles) {

        Set<String> uniqueLetterSet = new HashSet<>();
        char[] tileArray = tiles.toCharArray();
        Arrays.sort(tileArray);
        String sortedTiles = new String(tileArray);
        int output = generateSequences(sortedTiles, "", 0, uniqueLetterSet);
        return output - 1; // deduct 1 for empty string

    }

    private static int generateSequences(String tiles, String currentLetter, int index, Set<String> uniqueLetterSet) {
        // base case
        if (index >= tiles.length()) {
            if (!uniqueLetterSet.contains(currentLetter)) {
                uniqueLetterSet.add(currentLetter);
                return countPermutations(currentLetter);
            }
            return 0;
        }
        int withLetter = generateSequences(tiles, currentLetter + tiles.charAt(index), index + 1, uniqueLetterSet);
        int withoutLetter = generateSequences(tiles, currentLetter, index + 1, uniqueLetterSet);
        return withoutLetter + withLetter;
    }

    private static int countPermutations(String sequence) {
        int permutations = factorial(sequence.length());
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char ch : sequence.toCharArray()) {
            frequencyMap.put(ch, frequencyMap.getOrDefault(ch, 0) + 1);
        }
        int divisor = 1;
        for (int count : frequencyMap.values()) {
            divisor *= factorial(count);
        }
        return permutations / divisor;
    }

    private static int factorial(int n) {
        if (n <= 1)
            return 1;
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }


}
