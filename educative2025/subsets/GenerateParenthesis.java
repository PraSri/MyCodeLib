package package3;

import java.util.*;

public class GenerateParenthesis {

  public List<String> generateParenthesis(int n) {

    List<String> result = new ArrayList<>();
    List<Character> output = new ArrayList<>();

    backtrack(n, 0, 0, output, result);

    return result;

  }

  private void backtrack(int n, int leftCount, int rightCount, List<Character> output,
                         List<String> result) {
    // base case
    if (leftCount >= n && rightCount >= n) {
      String outputStr = output.toString();
      result.add(outputStr.substring(1, outputStr.length() - 1).replace(", ", ""));
    }
    // case when we still add left braces
    if (leftCount < n) {
      output.add('(');
      backtrack(n, leftCount + 1, rightCount, output, result);
      output.remove(output.size() - 1);
    }
    // case when add right braces when count of right is lesser than left
    if (rightCount < leftCount) {
      output.add(')');
      backtrack(n, leftCount, rightCount + 1, output, result);
      output.remove(output.size() - 1);
    }
  }

}
