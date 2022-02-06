package leetcode.slidingWindow;

public class LongestNiceSubstring {

    public static String longestNiceSubstring(String s) {
        String result = "";
        int n = s.length();
        for (int i = 0; i<n;i++){
            for (int j = i+1;j<=n;j++){
                String t = s.substring(i,j);

                if (
                        t.length() > 1
                        && result.length() < t.length()
                        && checkNice(t)
                ) {
                    result = t;
                }
            }
        }
        return result;
    }

    private static boolean checkNice(String t) {
        for (char ch : t.toCharArray()) {
             if (t.contains(Character.toUpperCase(ch) + "") != t.contains(Character.toLowerCase(ch) +"")) {
                 return false;
             }
        }
        return true;
    }
}
