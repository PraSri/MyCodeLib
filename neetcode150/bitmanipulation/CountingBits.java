package bitmanipulation;

public class CountingBits {

    // Power of 2 numbers (1, 2, 4, 8, …)
    // ke binary me sirf ek 1 hota hai

    public int[] countBits(int n) {
        int[] dp = new int[n+1];
        int offset = 1;
        for(int i = 1; i<=n; i++) {
            if(offset * 2 == i) {
                offset = i;
            }
            dp[i] = 1 + dp[i - offset];
        }
        return dp;
    }
}
