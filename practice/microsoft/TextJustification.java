package microsoft;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/text-justification/description/
public class TextJustification {

    public List<String> fullJustify(String[] words, int maxWidth) {
        int left = 0;
        List<String> result = new ArrayList<>();

        while (left < words.length) {
            int right = findRight(left, words, maxWidth);
            result.add(justify(left, right, words, maxWidth));
            left = right + 1;
        }

        return result;
    }

    // find the right as far as possible for each line
    private int findRight(int left, String[] words, int maxWidth) {
        int right = left;
        int sum = words[right++].length();

        while (right < words.length && (sum + 1 + words[right].length()) <= maxWidth)
            sum += 1 + words[right++].length();

        return right - 1;
    }

    // in cases we pad right side with spaces, until we reach max width of the line
    // 1. if its just one word, then that word is result
    // 2. if its last line then result is all words separated by single space
    // 3. Otherwise we calculate the size of each space evenly & if there is a remainder we distribute an extra space until it is gone
    private String justify(int left, int right, String[] words, int maxWidth) {
        if (right - left == 0) return padResult(words[left], maxWidth);

        boolean isLastLine = right == words.length - 1;
        int numSpaces = right - left;
        int totalSpace = maxWidth - wordsLength(left, right, words);

        String space = isLastLine ? " " : blank(totalSpace / numSpaces);
        int remainder = isLastLine ? 0 : totalSpace % numSpaces;

        StringBuilder result = new StringBuilder();
        for (int i = left; i <= right; i++)
            result.append(words[i])
                    .append(space)
                    .append(remainder-- > 0 ? " " : "");

        return padResult(result.toString().trim(), maxWidth);
    }

    // calculate length of words between two pointers
    private int wordsLength(int left, int right, String[] words) {
        int wordsLength = 0;
        for (int i = left; i <= right; i++) wordsLength += words[i].length();
        return wordsLength;
    }

    // pad the given string till maxWidth and return final string
    private String padResult(String result, int maxWidth) {
        return result + blank(maxWidth - result.length());
    }

    // return blank string with given length
    private String blank(int length) {
        return new String(new char[length]).replace('\0', ' ');
    }

}
