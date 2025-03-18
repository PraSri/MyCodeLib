package package4;

public class NumberStepsReduceNumberBinaryRepresentationOne {

  public static void main(String[] args) {
    System.out.println(numSteps("101"));
  }

  public static int numSteps(String s) {

    int length = s.length();

    int steps = 0;
    int carry = 0;

    for (int i = length - 1; i > 0; i--) {
      // consider carry from previous digit
      int digit = carry + s.charAt(i) - '0';
      // not even
      if (digit % 2 != 0) {
        steps += 2;
        carry = 1;
      } else {
        steps++;
      }
    }

    return steps + carry;

  }

  // dry run
  // s = "101" -> 5
  /**
   * rightmost bit = 1+0 -> odd
   * steps = 2
   * carry = 1
   *
   * then 0+1 -> odd
   * steps = 2+2=4
   * carry = 1
   *
   * // not consider this
   * then 1 -> odd
   * steps = 3+2=5
   * carry = 1
   *
   * total = steps + carry = 5 + 1 = 6
   *
   *
   * 1. 5+1=6
   * 2. 6/2 = 3
   * 3. 3+1=4
   * 4. 4/2=2
   * 5. 2/2=1
   *
   * */

}
