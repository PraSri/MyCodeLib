package mathandgeometry;

public class NonCyclicalNumber {
    public boolean isHappy(int n) {
        int slow = n;
        int fast = ss(n);
        while(slow!=fast) {
            slow = ss(slow);
            fast = ss(ss(fast));
        }
        return fast == 1;
    }

    private int ss(int n) {
        int res = 0;
        while(n!=0) {
            int digit = n%10;
            res = res + (digit * digit);
            n = n/10;
        }
        return res;
    }
}
