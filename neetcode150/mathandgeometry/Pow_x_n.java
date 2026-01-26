package mathandgeometry;

/**
 * Sqrt(x)  Easy
 * https://leetcode.com/problems/sqrtx/
 * <p>
 * Super Pow  Medium
 * https://leetcode.com/problems/super-pow/
 * <p>
 * Count Collisions of Monkeys on a Polygon  Medium
 * https://leetcode.com/problems/count-collisions-of-monkeys-on-a-polygon/
 **/

public class Pow_x_n {

    public double myPow(double x, int n) {
        if (x == 0)
            return 0;
        if (n == 0)
            return 1;
        double res = helper(x, Math.abs((long) n));
        return (n >= 0) ? res : 1 / res;
    }

    private double helper(double x, long n) {
        if (n == 0)
            return 1;
        double half = helper(x, n / 2);
        return (n % 2 == 0) ? half * half : x * half * half;
    }

    /**
     * Sqrt(x)  Easy
     * https://leetcode.com/problems/sqrtx
     */
    public static class SqrtXEasy {
    }

    /**
     * Super Pow  Medium
     * https://leetcode.com/problems/super-pow
     */
    public static class SuperPowMedium {
    }

    /**
     * Count Collisions of Monkeys on a Polygon  Medium
     * https://leetcode.com/problems/count-collisions-of-monkeys-on-a-polygon
     */
    public static class CountCollisionsOfMonkeysOnAPolygonMedium {
    }
}
