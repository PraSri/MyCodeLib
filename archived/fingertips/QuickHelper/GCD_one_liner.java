package QuickHelper;

public class GCD_one_liner {

	public static void main(String[] args) {
		System.out.println(gcd(98, 56));
	}

	/***** O(log(min(a,b))) *****/
	public static int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}
	
	static int gcd_v2(int a, int b)
    {
        // Everything divides 0 
        if (a == 0)
          return b;
        if (b == 0)
          return a;
      
        // base case
        if (a == b)
            return a;
      
        // a is greater
        if (a > b)
            return gcd(a-b, b);
        return gcd(a, b-a);
    }

}
