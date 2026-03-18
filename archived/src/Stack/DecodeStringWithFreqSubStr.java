package Stack;

import java.util.Stack;

public class DecodeStringWithFreqSubStr {

    // https://leetcode.com/problems/decode-string/

    public static void main(String[] args) {
        System.out.println(decodeString("3[a]2[bc]"));
    }

    public static String decodeString(String s) {

        StringBuilder res = new StringBuilder();

        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();

        int count = 0;

        for (char ch : s.toCharArray()) {

            // when you get digit, save in count variable

            if (Character.isDigit(ch)) {
                count = count * 10 + (ch - '0');
            }

            // for open bracket, push in stack, initialize variables
            else if (ch == '[') {
                countStack.push(count);
                count = 0;
                strStack.push(res);
                res = new StringBuilder();
            }

            // for closed bracket, pop from stacks & build final string
            else if (ch==']') {
                StringBuilder tmp = res;
                res = strStack.pop();
                int freq = countStack.pop();
                res.append(String.valueOf(tmp).repeat(Math.max(0, freq)));
            }

            else {
                res.append(ch);
            }
        }

        return res.toString();

    }

}
