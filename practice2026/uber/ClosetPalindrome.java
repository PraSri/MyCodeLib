package uber;

import java.util.Arrays;
import java.util.List;

public class ClosetPalindrome {

    public static void main(String[] args) {
        System.out.println("hello");
//        System.out.println(nearestPalindromic("12345"));
        int[] num = {9, 4, 1, 8, 7, 9, 7, 8, 3, 2, 2};
        //Output: [9, 4, 1, 8, 8, 0, 8, 8, 1, 4, 9]
        String string = Arrays.toString(num);

        StringBuilder sb = new StringBuilder();

        for (int n : num) {
            sb.append(n);
        }

        String result = sb.toString();
        System.out.println(result);
        System.out.println(nearestPalindromic(result, true));

    }

    public static String nearestPalindromic(String n, boolean isStrictlyLarger) {
        int len = n.length();
        int i = len % 2 == 0 ? len / 2 - 1 : len / 2;
        long firstHalf = Long.parseLong(n.substring(0, i + 1));

        // all possibilities
        boolean isEvenLen = len % 2 == 0;
        long p1 = buildPalindrome(firstHalf, isEvenLen);
        long p2 = buildPalindrome(firstHalf + 1, isEvenLen);
        long p3 = buildPalindrome(firstHalf - 1, isEvenLen);
        long p4 = (long) (Math.pow(10, len - 1) - 1);
        long p5 = (long) (Math.pow(10, len - 1) + 1);

        List<Long> possibilities = List.of(p1, p2, p3, p4, p5);
        long nl = Long.parseLong(n);
        long diff = Long.MAX_VALUE;
        long res = 0L;
        if (isStrictlyLarger) {
            for (Long candidate : possibilities) {
                if (candidate == nl) continue;
                if (Math.abs(candidate - nl) < diff && candidate > nl) {
                    diff = Math.abs(candidate - nl);
                    res = candidate;
                } else if (Math.abs(candidate - nl) == diff) {
                    res = Math.min(res, candidate);
                }
            }
        } else {
            for (Long candidate : possibilities) {
                if (candidate == nl) continue;
                if (Math.abs(candidate - nl) < diff) {
                    diff = Math.abs(candidate - nl);
                    res = candidate;
                } else if (Math.abs(candidate - nl) == diff) {
                    res = Math.min(res, candidate);
                }
            }
        }


        return String.valueOf(res);

    }

    private static Long buildPalindrome(long left, boolean isEvenLength) {
        long res = left;
        if (!isEvenLength) {
            left = left / 10;
        }
        while (left > 0) {
            res = (res * 10) + (left % 10);
            left = left / 10;
        }
        return res;
    }
}
