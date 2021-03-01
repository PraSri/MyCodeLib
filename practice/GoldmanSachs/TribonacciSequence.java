package GoldmanSachs;

public class TribonacciSequence {
	
	public static void main(String[] args) {
		printTrib3var(10);
	}
	static int printTribRec(int n) {

		if (n == 0 || n == 1 || n == 2)
			return 0;

		if (n == 3)
			return 1;
		else
			return printTribRec(n - 1) + printTribRec(n - 2) + printTribRec(n - 3);
	}

	static void printTrib(int n) {
		for (int i = 1; i < n; i++)
			System.out.print(printTribRec(i) + " ");
	}

	static void printTribDP(int n) {
		int dp[] = new int[n];
		dp[0] = dp[1] = 0;
		dp[2] = 1;

		for (int i = 3; i < n; i++)
			dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];

		for (int i = 0; i < n; i++)
			System.out.print(dp[i] + " ");
	}
	
	static void printTrib3var(int n) 
    { 
        if (n < 1) 
            return; 
      
        // Initialize first 
        // three numbers 
        int first = 0, second = 0; 
        int third = 1; 
      
        System.out.print(first + " "); 
        if (n > 1) 
            System.out.print(second + " "); 
          
        if (n > 2) 
            System.out.print(second + " "); 
      
        // Loop to add previous 
        // three numbers for 
        // each number starting 
        // from 3 and then assign 
        // first, second, third 
        // to second, third, and curr 
        // to third respectively 
        for (int i = 3; i < n; i++)  
        { 
            int curr = first + second + third; 
            first = second; 
            second = third; 
            third = curr; 
      
            System.out.print(curr +" "); 
        } 
    } 
}
