package mathandgeometry;

public class MultiplyStrings {

    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0"))
            return "0";
        if (num1.length() < num2.length()) {
            return multiply(num2, num1);
        }
        String res = "";
        int zero = 0;
        for (int i = num2.length() - 1; i >= 0; i--) {
            String cur = mul(num1, num2.charAt(i), zero);
            res = add(res, cur);
            zero++;
        }
        return res;
    }

    private String add(String x, String y) {
        int i = x.length() - 1;
        int j = y.length() - 1;
        int carry = 0;
        StringBuilder res = new StringBuilder();
        while (i >= 0 || j >= 0 || carry > 0) {
            int n1 = (i >= 0) ? x.charAt(i) - '0' : 0;
            int n2 = (j >= 0) ? y.charAt(j) - '0' : 0;
            int total = n1 + n2 + carry;
            res.append(total % 10);
            carry = total / 10;
            i--;
            j--;
        }
        return res.reverse().toString();
    }

    private String mul(String x, char d, int zero) {
        int i = x.length() - 1;
        int carry = 0;
        int digit = d - '0';
        StringBuilder res = new StringBuilder();
        while (i >= 0 || carry > 0) {
            int n = (i >= 0) ? x.charAt(i) - '0' : 0;
            int prod = n * digit + carry;
            res.append(prod % 10);
            carry = prod / 10;
            i--;
        }
        return res.reverse().toString() + "0".repeat(zero);
    }
}
