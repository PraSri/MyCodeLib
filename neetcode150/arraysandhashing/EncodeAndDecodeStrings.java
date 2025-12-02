package arraysandhashing;

import java.util.*;

class EncodeAndDecodeStrings {

/**
 * Encodes a list of strings to a single string and decodes it back to the list of strings.
 * <p>
 * The encoding strategy uses a length-prefix format: "length#string".
 * For example, ["hello", "world"] becomes "5#hello5#world".
 * <p>
 * Algorithm:
 * 1. Encode: Iterate through each string, append its length, a delimiter '#', and the string itself.
 * 2. Decode: Parse the integer before '#', use it to read the exact number of characters for the string,
 *    and repeat until the end of the encoded string.
 * <p>
 * Time Complexity: O(m)
 * Space Complexity: O(m + n)
 * where m is the length of the encoded string and n is the number of strings
 */


    public String encode(List<String> strs) {
        StringBuilder res = new StringBuilder();
        for (String s : strs) {
            res.append(s.length()).append('#').append(s);
        }
        return res.toString();
    }

    public List<String> decode(String str) {
        List<String> res = new ArrayList<>();
        int i = 0;
        while (i < str.length()) {
            int j = i;
            while (str.charAt(j) != '#') {
                j++;
            }
            int length = Integer.parseInt(str.substring(i, j));
            i = j + 1;
            j = i + length;
            res.add(str.substring(i, j));
            i = j;
        }
        return res;
    }
}
