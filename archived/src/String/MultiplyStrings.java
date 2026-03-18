package String;

public class MultiplyStrings {

  // https://leetcode.com/problems/multiply-strings/discuss/17605/Easiest-JAVA-Solution-with-Graph-Explanation
  
  public String multiply(String num1, String num2) {
        int n = num1.length();
        int m = num2.length();
        int[] p = new int[n + m];
        
        for(int i = n-1; i>=0; i--) {
            for(int j = m-1; j>=0; j--) {
                
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i+j;
                int p2 = i+j+1;
                
                int sum = mul + p[p2];
                
                p[p1] += sum/10;
                p[p2] = sum%10;
                
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int x : p) {
            if(!(sb.length() == 0 && x==0)) {
                sb.append(x);
            }
        }
        
        return sb.length() == 0 ? "0" : sb.toString();
        
    }

}
