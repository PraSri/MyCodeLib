package String;

public class AddStrings {

public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int l1 = num1.length();
        int l2 = num2.length();
        int i = l1-1, j = l2-1;
        int c = 0;
        while(i>=0 || j>=0 || c==1) {
            int d1 = i<0 ? 0 : num1.charAt(i) - '0';
            int d2 = j<0 ? 0 : num2.charAt(j) - '0';
            sb.append((d1+d2+c)%10);
            c = (d1+d2+c)/10;
            i--;
            j--;
        }
        
        return sb.reverse().toString();
    }

}
