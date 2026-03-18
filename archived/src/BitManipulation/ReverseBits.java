package BitManipulation;

public class ReverseBits {

	public static void main(String[] args) {

	}

	// Reverse the bits of an 32 bit unsigned integer A.
	
	
	public long reverse(long a) {
	    long n = a;
        long x = 0;
        int  i = 31;
        while(n!=0 && i>=0){
           x |=(n&1)<<i;
           i--;
           n>>=1;
            // System.out.println(n);
        }
        // System.out.println(x);//536870912
        return x;
	}
}
