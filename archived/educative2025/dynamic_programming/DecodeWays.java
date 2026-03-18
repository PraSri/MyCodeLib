package dynamic_programming;

import java.util.*;

public class DecodeWays {
    public static int numOfDecodings(String decodeStr) {
        // initialize the dp array with all 0s
        int strLen = decodeStr.length();
        int[] dp = new int[strLen + 1];
        // there is only one way to decode an empty string
        dp[0] = 1;

        // the first element of the dp array is 1 if the first character
        // of the string is not '0', otherwise it's 0
        if (decodeStr.charAt(0) != '0') {
            dp[1] = 1;
        } else {
            return 0;
        }

        // iterate through the input string starting from the 2nd character
        for (int i = 2; i <= strLen; ++i) {
            // if the current character is not '0', add the number of ways
            // to decode the substring without the current character
            if (decodeStr.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }
            // if the substring of the current and previous characters is a valid
            // two-digit number, add the number of ways to decode the substring
            // without the current and previous characters
            if (decodeStr.charAt(i - 2) == '1' || (decodeStr.charAt(i - 2) == '2' && decodeStr.charAt(i - 1) <= '6')) {
                dp[i] += dp[i - 2];
            }
        }

        // return the number of ways to decode the input string
        return dp[strLen];
    }

    public static int numOfDecodingsSpaceOptimized(String decodeStr) {
        int strLen = decodeStr.length();
        if (strLen == 0 || decodeStr.charAt(0) == '0') {
            return 0; // No valid decoding if the string is empty or starts with '0'.
        }

        // Initialize variables for previous and current decoding counts
        int prev = 1; // Represents dp[i-2]
        int curr = 1; // Represents dp[i-1]

        for (int i = 1; i < strLen; ++i) {
            int temp = 0; // Temp variable to store the new curr value

            // Check if the current character can be decoded as a single digit
            if (decodeStr.charAt(i) != '0') {
                temp += curr;
            }

            // Check if the two-character substring is a valid double-digit number
            if (decodeStr.charAt(i - 1) == '1' ||
                    (decodeStr.charAt(i - 1) == '2' && decodeStr.charAt(i) <= '6')) {
                temp += prev;
            }

            // Update prev and curr for the next iteration
            prev = curr;
            curr = temp;
        }

        return curr; // The last curr value holds the result
    }

    // Driver code
    public static void main(String[] args) {
        List<String> decodeStr = Arrays.asList("124", "123456", "11223344", "0", "0911241", "10203", "999901");

        for (int i = 0; i < decodeStr.size(); ++i) {
            System.out.println((i + 1) + ".\t There are " + numOfDecodingsSpaceOptimized(decodeStr.get(i))
                    + " ways in which we can decode the string: '" + decodeStr.get(i) + "'");
            System.out.println(new String(new char[100]).replace("\0", "-"));
        }
    }
}