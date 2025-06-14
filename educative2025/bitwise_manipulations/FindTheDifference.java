package bitwise_manipulations;

public class FindTheDifference {
    public char findTheDifference(String s, String t) {

        int result = 0;
        int n = s.length();
        int m = t.length();
        for (int i = 0; i < n; i++) {
            result ^= s.charAt(i);
        }

        for (int i = 0; i < m; i++) {
            result ^= t.charAt(i);
        }

        return (char) result;

    }

    public static void main(String[] args) {
        FindTheDifference obj = new FindTheDifference();
        System.out.println(obj.findTheDifference("abcd", "abcde"));
    }
}
