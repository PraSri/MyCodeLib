package package3;

import java.util.ArrayList;
import java.util.List;

public class LargestPalindromicNumber {

  public static String largestPalindromic(String num) {

    int[] frequency = new int[10];
    for (char c : num.toCharArray()) {
      frequency[c - '0']++;
    }

    List<String> firstHalf = new ArrayList<>();
    String middle = "";

    for (char digit = '9'; digit >= '0'; digit--) {
      if (frequency[digit - '0'] != 0) {
        int count = frequency[digit - '0'];
        int numPairs = count / 2;
        if (numPairs > 0) {
          if (firstHalf.isEmpty() && digit == '0') {
            frequency[0] = 1;
          } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < numPairs; i++) {
              sb.append(digit);
            }
            firstHalf.add(sb.toString());
          }
        }
        if (count % 2 == 1 && middle.isEmpty()) {
          middle = Character.toString(digit);
        }
      }
    }

    if (middle.isEmpty() && firstHalf.isEmpty()) {
      return "0";
    }

    StringBuilder result = new StringBuilder();
    for (String part : firstHalf) {
      result.append(part);
    }

    String secondHalf = result.toString();

    result.append(middle).append(new StringBuilder(secondHalf).reverse());

    return result.toString();

  }

  public static void main(String[] args) {
    String[] numbers = {"00001", "1234287", "9876545367282", "000000", "146"};
    String ans = largestPalindromic(numbers[0]);
    System.out.println(ans);
  }

}
