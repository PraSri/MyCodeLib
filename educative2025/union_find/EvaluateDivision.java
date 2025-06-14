package union_find;

import java.util.*;

/***
 * Solution steps
 * Initialize the union find structure so that we can group our variables. Variables that are connected by a chain of divisions should be in the same group. Additionally, set up the dictionary that stores the variables with the respective weights and groups they belong to. The weight of every variable is initially set to 1.
 *
 * Iterate through the list of input equations, invoking union(dividend, divisor, quotient) with each in order to populate our union find structure.
 *
 * If either of the query variables do not appear in the input equations, return -1.0.
 *
 * Otherwise, if both variables do appear in the input equations, use the find(variable) function to get the group and weight of each of them. The find(variable) function will update the weights in case of any discrepancies.
 *
 * If both variables belong to the same group, a chain of division exists between them and we can return the division of their weights as the result.
 *
 * Otherwise, if the two variables don’t belong to the same group, they aren’t connected by a chain of divisions and we return -1.0 as result.*/

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
            parent.put(dividend, dividend);
            weight.put(dividend, 1.0);
        }
        if (!parent.containsKey(divisor)) {
            parent.put(divisor, divisor);
            weight.put(divisor, 1.0);
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
            union(equations.get(i).get(0), equations.get(i).get(1), values[i]);
        }

        double[] results = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String var1 = queries.get(i).get(0);
            String var2 = queries.get(i).get(1);

            if (!parent.containsKey(var1) || !parent.containsKey(var2)) {
                results[i] = -1.0;
            } else {
                String root1 = find(var1);
                String root2 = find(var2);

                if (!root1.equals(root2)) {
                    results[i] = -1.0;
                } else {
                    results[i] = weight.get(var1) / weight.get(var2);
                }
            }
        }
        return results;
    }

    static class Main {
        public static void main(String[] args) {
            EvaluateDivision evaluator = new EvaluateDivision();
            List<List<String>> equations = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c"));
            double[] values = {2.0, 3.0};
            List<List<String>> queries = Arrays.asList(Arrays.asList("a", "c"), Arrays.asList("b", "a"), Arrays.asList("a", "e"), Arrays.asList("x", "y"));

            double[] results = evaluator.evaluateEquations(equations, values, queries);

            System.out.println(Arrays.toString(results)); // Output: [6.0, 0.5, -1.0, -1.0]
        }
    }
}

