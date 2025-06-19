package union_find;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/***
 * Solution steps
 * <p>
 * Initialize the union find structure so that we can group our variables.
 * Variables that are connected by a chain of divisions should be in the same group.
 * Additionally, set up the dictionary that stores the variables with the respective weights and
 * groups they belong to. The weight of every variable is initially set to 1.
 * <p>
 * Iterate through the list of input equations,
 * invoking union(dividend, divisor, quotient) with each in order to populate our union find structure.
 * <p>
 * If either of the query variables do not appear in the input equations, return -1.0.
 * <p>
 * Otherwise, if both variables do appear in the input equations,
 * use the find(variable) function to get the group and weight of each of them.
 * The find(variable) function will update the weights in case of any discrepancies.
 * <p>
 * If both variables belong to the same group,
 * a chain of division exists between them and we can return the division of their weights as the result.
 * <p>
 * Otherwise, if the two variables don’t belong to the same group,
 * they aren’t connected by a chain of divisions and we return -1.0 as result.
 * <p>
 *     Time complexity : O(E + Q), given E is the number of equations, and Q is the number of queries.
 *     Space complexity : O(V + Q), where V is the number of distinct variables, and Q is the number of queries.
 * </p>
 * */

public class EvaluateDivision {
    private Map<String, String> parent;
    private Map<String, Double> weight;

    public EvaluateDivision() {
        parent = new HashMap<>();
        weight = new HashMap<>();
    }

    private String find(String variable) {
        if (!parent.containsKey(variable)) return null;
        if (!variable.equals(parent.get(variable))) {
            String originalParent = parent.get(variable);
            parent.put(variable, find(originalParent));
            weight.put(variable, weight.get(variable) * weight.get(originalParent));
        }
        return parent.get(variable);
    }

    private void union(String dividend, String divisor, double quotient) {
        if (!parent.containsKey(dividend)) {
            parent.put(dividend, dividend); // a -> a
            weight.put(dividend, 1.0); // a -> 1.0
        }
        if (!parent.containsKey(divisor)) {
            parent.put(divisor, divisor); // b -> b
            weight.put(divisor, 1.0); // b -> 1.0
        }

        String rootDividend = find(dividend);
        String rootDivisor = find(divisor);

        if (!rootDividend.equals(rootDivisor)) {
            parent.put(rootDividend, rootDivisor);
            weight.put(rootDividend, quotient * weight.get(divisor) / weight.get(dividend));
        }
    }

    public double[] evaluateEquations(List<List<String>> equations, double[] values, List<List<String>> queries) {
        for (int i = 0; i < equations.size(); i++) {
            String dividend = equations.get(i).get(0); // a
            String divisor = equations.get(i).get(1); // b
            double quotient = values[i]; // 2
            union(dividend, divisor, quotient);
        }

        // queries = [[a,c]. [b,a], [a,e], [x,y]]
        double[] results = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String var1 = queries.get(i).get(0); // a
            String var2 = queries.get(i).get(1); // c

            if (!parent.containsKey(var1) || !parent.containsKey(var2)) {
                results[i] = -1.0;
            } else {
                String root1 = find(var1);
                String root2 = find(var2);

                if (!root1.equals(root2)) {
                    results[i] = -1.0;
                } else {
                    // a/c =? a/b, b/c w[a] = 2 * (b/a), w[b] = 2 * (c/b), result[i] = w[a]/w[b] = (b/a)/(c/b)
                    results[i] = weight.get(var1) / weight.get(var2);
                }
            }
        }
        return results;
    }

    public static class Rate {
        String from;
        String to;
        double value;

        public Rate(String from, String to, double value) {
            this.from = from;
            this.to = to;
            this.value = value;
        }
    }

    public double currencyConversion(Rate[] rates, String[] convert) {
        List<List<String>> equations = new ArrayList<>();
        double[] values = new double[rates.length];
        int i = 0;
        for (Rate rate : rates) {
            List<String> temp = new ArrayList<>();
            temp.add(rate.from);
            temp.add(rate.to);
            equations.add(temp);
            values[i++] = rate.value;
        }

        List<List<String>> queries = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        temp.add(convert[0]);
        temp.add(convert[1]);
        queries.add(temp);
        return evaluateEquations(equations, values, queries)[0];
    }

    static class Main {
        public static void main(String[] args) {
            EvaluateDivision evaluator = new EvaluateDivision();
            List<List<String>> equations = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c"));
            double[] values = {2.0, 3.0};
            List<List<String>> queries = Arrays.asList(Arrays.asList("a", "c"), Arrays.asList("b", "a"), Arrays.asList("a", "e"), Arrays.asList("x", "y"));

            double[] results = evaluator.evaluateEquations(equations, values, queries);

            System.out.println(Arrays.toString(results)); // Output: [6.0, 0.5, -1.0, -1.0]

            System.out.println("***********CURRENCY CONVERTER****************");

            /**Rates: ['USD', 'JPY', 110] ['US', 'AUD', 1.45] ['JPY', 'GBP', 0.0070]
             To/From currency ['GBP', 'AUD']*/

            Rate r1 = new Rate("US", "JPY", 110.0);
            Rate r2 = new Rate("US", "AUD", 1.45);
            Rate r3 = new Rate("JPY", "GBP", 0.0070);
            String[] convert = new String[]{"GBP", "AUD"};
            Rate[] rates = new Rate[]{r1, r2, r3};
            double ans = evaluator.currencyConversion(rates, convert);
            BigDecimal bigDecimal = new BigDecimal(ans).setScale(2, RoundingMode.CEILING);
            System.out.println(bigDecimal);

            int x = 0 >> 1;
            int y = 1 >> 1;
            int z = 2 >> 1;
            int w = 3 >> 1;
            System.out.println(x + "," + y + "," + z + "," + w);
        }
    }
}

