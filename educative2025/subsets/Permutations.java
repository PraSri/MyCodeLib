package subsets;

import java.util.*;

public class Permutations {

    /**
     * Given an input string, word, return all possible permutations of the string.
     */
    public static String swapChar(String word, int i, int j) {
        char[] swapIndex = word.toCharArray();
        char temp = swapIndex[i];
        swapIndex[i] = swapIndex[j];
        swapIndex[j] = temp;

        return new String(swapIndex);
    }

    public static void permuteStringRec(String word, int currentIndex, ArrayList<String> results) {
        if (currentIndex == word.length() - 1) {
            results.add(word);
            return;
        }
        for (int index = currentIndex; index < word.length(); index++) {
            String swappedStr = swapChar(word, currentIndex, index);
            permuteStringRec(swappedStr, currentIndex + 1, results);
        }
    }

    public static ArrayList<String> permuteWord(String word) {
        ArrayList<String> results = new ArrayList<String>();
        permuteStringRec(word, 0, results);
        return results;
    }

    // Driver code
    public static void main(String args[]) {
        String[] inputWord = {"ab", "bad", "abcd"};
        for (int index = 0; index < inputWord.length; index++) {
            ArrayList<String> permutedWords = permuteWord(inputWord[index]);
            System.out.println(index + 1 + ".\t Input string: '" + inputWord[index] + "'");
            System.out.println("\t All possible permutations are: " + permutedWords);
        }
    }
}
