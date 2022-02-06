package leetcode.slidingWindow;

public class LongestSubstringWithAtMostTwoDistinctCharacters {

     public static void main(String[] args) {
        // Prints "Hello, World" to the terminal window.
        System.out.println("Hello, World");
        System.out.print(getLongestSubString("eceba"));
    }

    public static int getLongestSubString(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int start = 0, end = 0, counter = 0;
        int[] frequency = new int[256];
        int bestLen = -1;
        while (end < s.length()) {
            //expansion
            if (frequency[s.charAt(end)] == 0) {
                counter++;
            }
            frequency[s.charAt(end)]++;
            end++;

            // contraction
            while (counter > 2) {
                if (frequency[s.charAt(start)] == 1) {
                    counter--;
                }
                frequency[s.charAt(start)]--;
                start++;
            }

            bestLen = Math.max(bestLen, end - start);
        }
        return bestLen;
    }
}
