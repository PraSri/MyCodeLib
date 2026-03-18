package bitwise_manipulations;

public class ComplementOfBase10Integer {

    public int bitwiseComplement(int n) {
        if(n == 0)
            return 1;
        if(n==1)
            return 0;
        int x = 1; // 0001
        while(n>x)
            x = x * 2 + 1; // 1st iteration : x = 3 => 011 // 2nd : x = 7 => 111 .... its adding binary 1 till it crosses n
        return n ^ x;
    }
}
