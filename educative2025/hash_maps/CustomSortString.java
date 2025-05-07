package hash_maps;

import java.util.HashMap;
import java.util.Map;

public class CustomSortString {

    public static String customSortString(String order, String s) {

        // Create a hash map to store the count of each character in s
        Map<Character, Integer> frequencies = new HashMap<>();

        // Iterate through s and update the hash map with the frequency of each character
        for (char c : s.toCharArray()) {
            frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
        }

        // Define result to build the final string
        StringBuilder result = new StringBuilder();

        // Iterate through the order string
        for (char c : order.toCharArray()) {
            if (frequencies.containsKey(c)) {

                // Append c to result as many times as its recorded frequency
                for (int i = 0; i < frequencies.get(c); i++) {
                    result.append(c);
                }

                // Remove c from the hash map
                frequencies.remove(c);
            }
        }

        // Append remaining characters to result
        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            char c = entry.getKey();
            int count = entry.getValue();
            for (int i = 0; i < count; i++) {
                result.append(c);
            }
        }

        // Return the result string
        return result.toString();
    }

    // Driver code
    public static void main(String[] args) {
        String[][] testCases = {
                {"cba", "abcd"},
                {"xyz", "abcdef"},
                {"bca", "xyz"},
                {"edcba", "abcde"},
                {"abc", "aabbcc"},
                {"abc", "xyz"},
                {"wo", "meow"}
        };

        for (int i = 0; i < testCases.length; i++) {
            String order = testCases[i][0];
            String s = testCases[i][1];
            System.out.println((i + 1) + ".\torder = \"" + order + "\"");
            System.out.println("\ts = \"" + s + "\"");
            System.out.println("\n\tresult = \"" + customSortString(order, s) + "\"");
            System.out.println(new String(new char[100]).replace("\0", "-"));
        }
    }
}
