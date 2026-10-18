package math.multiply_strings;

public class Solution {

    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int m = num1.length();
        int n = num2.length();
        int[] product = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            int a = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int sum = product[i + j + 1] + a * (num2.charAt(j) - '0');
                product[i + j + 1] = sum % 10;
                product[i + j] += sum / 10;
            }
        }
        StringBuilder sb = new StringBuilder(m + n);
        for (int digit : product) {
            if (sb.length() == 0 && digit == 0) {
                continue;
            }
            sb.append(digit);
        }
        return sb.toString();
    }
}
