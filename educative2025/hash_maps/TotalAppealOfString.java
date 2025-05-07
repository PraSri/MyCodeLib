package hash_maps;

import java.util.Arrays;

public class TotalAppealOfString {
    public static int appealSum(String s) {
        // Create an array to track the last occurrence index of each character
        int[] track = new int[26];

        // Initialize it with -1
        Arrays.fill(track, -1);

        // Initialize result variable to accumulate the total appeal sum
        int res = 0;

        // Get the length of the string
        int n = s.length();

        // Iterate through the string
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            // Calculate the contribution of the current character to the appeal sum
            // (i - track[c - 'a']) gives the number of substrings ending with c
            // (n - i) gives the number of substrings starting from index i to the end of the string
            res += (i - track[c - 'a']) * (n - i);

            // Update the last occurrence index of c to the current index
            track[c - 'a'] = i;
        }

        // Return the total appeal sum
        return res;
    }

    // Driver code
    public static void main(String[] args) {
        String[] strs = {"asd", "bbb", "q", "madam", "hippopotamus"};

        for (int index = 0; index < strs.length; index++) {
            String stringValue = strs[index];
            System.out.println((index + 1) + ".\ts: " + stringValue);
            System.out.println("\tTotal appeal: " + appealSum(stringValue));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
