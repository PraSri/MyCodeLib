package tree_dfs;

import java.util.*;

public class NestedListWeightSumII {

    public static class NestedInteger {
        private List<NestedInteger> nList;
        private int integer;
        private boolean isSingleInteger;

        // Constructor initializes an empty nested list
        public NestedInteger() {
            nList = new ArrayList<>();
            isSingleInteger = false;
        }

        // Constructor initializes a single integer
        public NestedInteger(int value) {
            integer = value;
            isSingleInteger = true;
        }

        // Returns true if this NestedInteger holds a single integer rather than a nested list
        public boolean isInteger() {
            return isSingleInteger;
        }

        // Returns the single integer this NestedInteger holds, if it holds a single integer
        // Otherwise, return null if this NestedInteger holds a nested list
        public Integer getInteger() {
            return isSingleInteger ? integer : null;
        }

        // Sets this NestedInteger to hold a single integer equal to value
        public void setInteger(int value) {
            integer = value;
            isSingleInteger = true;
        }

        // Sets this NestedInteger to hold a nested list and adds the nested integer elem to it
        public void add(NestedInteger elem) {
            if (isSingleInteger) {
                nList = new ArrayList<>();
            }
            nList.add(elem);
            isSingleInteger = false;
        }

        // Returns the nested list that this NestedInteger holds, if it holds a nested list
        // Otherwise, return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList() {
            return nList;
        }
    }

    public static class NestedIntegerParser {
        public static List<NestedInteger> createNestedInteger(String str) {
            Stack<NestedInteger> stack = new Stack<>();
            stack.push(new NestedInteger());

            int index = 0;

            while (index < str.length()) {
                if (Character.isDigit(str.charAt(index)) || (str.charAt(index) == '-' && Character.isDigit(str.charAt(index + 1)))) {
                    int start = index;
                    while (index < str.length() && (Character.isDigit(str.charAt(index)) || str.charAt(index) == '-')) {
                        index++;
                    }

                    int value = Integer.parseInt(str.substring(start, index));
                    stack.peek().add(new NestedInteger(value));
                } else if (str.charAt(index) == '[') {
                    index++;
                    NestedInteger nestedList = new NestedInteger();
                    stack.push(nestedList);
                } else if (str.charAt(index) == ',') {
                    index++;
                } else if (str.charAt(index) == ']') {
                    NestedInteger top = stack.pop();
                    stack.peek().add(top);
                    index++;
                } else {
                    index++;
                }
            }

            List<NestedInteger> ret = new ArrayList<>();
            ret.add(stack.peek());
            return ret;
        }
    }

    public static class Solution {

        // Function to recursively calculate the maximum depth
        static int findMaxDepth(List<NestedInteger> nestedList) {
            int maxDepth = 0;

            for (NestedInteger ni : nestedList) {
                // If the nested object is a list with length greater than 0
                if (!ni.isInteger() && !ni.getList().isEmpty()) {
                    maxDepth = Math.max(maxDepth, 1 + findMaxDepth(ni.getList()));
                }
            }

            return maxDepth;
        }

        static int weightedDepthSumRec(List<NestedInteger> nestedList, int depth, int maxDepth) {
            int result = 0;
            for (NestedInteger ni : nestedList) {
                // If the nested object is an integer, multiply its value
                // with its weight and add the answer to result
                if (ni.isInteger()) {
                    result += ni.getInteger() * (maxDepth - depth + 1);
                } else {
                    // If the nested object is a list, call the recursive
                    // function with depth+1
                    result += weightedDepthSumRec(ni.getList(), depth + 1, maxDepth);
                }
            }

            return result;
        }

        public static int weightedDepthSum(List<NestedInteger> nestedList) {
            // Calculate the maximum depth
            int maxDepth = findMaxDepth(nestedList);
            System.out.println("maxDepth : " + maxDepth);

            // Calculate the recursive function to calculate the weighted sum
            return weightedDepthSumRec(nestedList, 0, maxDepth);
        }

        public static void main(String[] args) {
            List<String> nestedIntegerStrings = new ArrayList<>();
            nestedIntegerStrings.add("[1, [2, 3], 4]");
            nestedIntegerStrings.add("[[1, 1], 2, [1, [2, [1]]]]");
            nestedIntegerStrings.add("[[1, 2], [3, 4], [5, 6]]");
            nestedIntegerStrings.add("[1, [2, [3, [4, [5]]]]]");
            nestedIntegerStrings.add("[[[[[[1]]]]]]");

            for (int i = 0; i < nestedIntegerStrings.size(); ++i) {
                List<NestedInteger> nestedInteger = NestedIntegerParser.createNestedInteger(nestedIntegerStrings.get(i));
                System.out.println((i + 1) + ".\tNested list: " + nestedIntegerStrings.get(i));
                System.out.println("\tWeighted sum: " + weightedDepthSum(nestedInteger));
                System.out.println(new String(new char[100]).replace("\0", "-"));
            }
        }
    }
}
