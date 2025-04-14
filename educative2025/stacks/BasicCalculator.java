package stacks;

import java.util.*;

public class BasicCalculator {

    public static int calculator(String expression) {
        int signValue = 1;
        int number = 0;
        int result = 0;
        int secondValue = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (Character.isDigit(c)) {
                number = number * 10 + Character.getNumericValue(c);
            } else if (c == '+' || c == '-') {
                result += number * signValue;
                if (c == '-')
                    signValue = -1;
                else
                    signValue = 1;
                number = 0;
            } else if (c == '(') {
                stack.push(result);
                stack.push(signValue);
                result = 0;
                signValue = 1;
            } else if (c == ')') {
                result += signValue * number;
                int popSignValue = stack.pop();
                result *= popSignValue;

                secondValue = stack.pop();
                result += secondValue;
                number = 0;
            }
        }
        return result + number * signValue;
    }

    public static void main(String[] args) {
        String[] input = {
                "4 + (52 - 12) + 99",
                "(31 + 7) - (5 - 2)",
                "(12 - 9 + 4) + (7 - 5)",
                "8 - 5 + (19 - 11) + 6 + (10 + 3)",
                "56 - 44 - (27 - 17 - 1) + 7"
        };
        for (int i = 0; i < input.length; i++) {
            System.out.println((i + 1) + "." + "\tGiven Expression: " + input[i]);
            System.out.println("\tThe result is:  " + calculator(input[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}