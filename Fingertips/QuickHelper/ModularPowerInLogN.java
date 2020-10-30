package QuickHelper;

public class ModularPowerInLogN {
	
	
	public static void main(String[] args) {
		
		System.out.println(fastPower(2, 4, 1000000000)); // output = 16
	}

	public static int fastPower(long a, long b, long mod) {
		
		
		long res = 1; // a^b
		
		
		while (b > 0) {
			
			// b&1 => isolates the last bit and check is it 1?
			if ((b & 1) == 1) {
				
				// if yes then it means that the power number(b) is odd
				
				res = res * a % mod;
				
				// save the result like this : if a = 2 b =  5 a^b = 2^5 res = res * 2 
				// res = 1*2 = 2;
				
			}
			
			// update a : a = 2 * 2=4
			a = a * a % mod;

			// divide b by 2 : b = b>>1 => b = 2.5 => b=2
			b >>= 1;
		}
		
		
		return (int) res;
	}
}
