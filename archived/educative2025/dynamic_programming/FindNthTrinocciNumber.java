package dynamic_programming;

public class FindNthTrinocciNumber {

    public static int findTribonacci(int n) {
        // if n is less than 3, then it's the base case
        if (n < 3)
            return n == 0 ? 0 : 1;
        // initializing first three numbers
        int firstNum = 0, secondNum = 1, thirdNum = 1;
        int temp = 0;
        // loop proceeds for n-2 times
        for (int i = 3; i <= n; i++) {
            // temp stores sum of last three computed Tribonacci numbers
            temp = firstNum + secondNum + thirdNum;
            // replaces firstNum by secondNum
            firstNum = secondNum;
            // replaces secondNum by thirdNum
            secondNum = thirdNum;
            // replaces thirdNum by sum of last three
            // computed Tribonacci numbers, which are stored in temp
            thirdNum = temp;
        }
        // returns nth Tribonacci number
        return thirdNum;
    }

    // Driver code
    public static void main(String[] args) {
        int[] inputList = {4, 5, 25};
        int index = 0;
        for (int input : inputList) {
            System.out.println((++index) + ". The " + input + "th Tribonacci number is:  "
                    + findTribonacci(input));
            System.out.println("-".repeat(100));
        }
    }


}
