package package3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsOfPhoneNumber {

  private static final String[] keys =
      new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

  public static List<String> letterCombinations(String digits) {

    List<String> result = new ArrayList<>();

    if (digits == null || digits.isEmpty()) {
      return result;
    }

    solve(0, "", digits, result);

    return result;
  }

  private static void solve(int index, String path, String digits, List<String> result) {
    if (index >= digits.length()) {
      result.add(path);
      return;
    }
    String letters = keys[digits.charAt(index) - '0'];
    for (int i = 0; i < letters.length(); i++) {
      solve(index + 1, path + letters.charAt(i), digits, result);
    }
  }

  public static void main(String[] args) {
    System.out.println(letterCombinations("23"));
  }

  // Use backtrack function to generate all possible combinations
  public void backtrack(int index, StringBuilder path, String digits,
                        Map<Character, String[]> letters, List<String> combinations) {
    
    // If the length of path and digits is same,
    // we have a complete combination
    if (path.length() == digits.length()) {
      
      // Join the path list into a string and add it to combinations list
      combinations.add(path.toString());
      return; // Backtrack;
    }
    // Get the list of letters using the index and digits[index]
    char digit = digits.charAt(index);
    String[] possibleLetters = letters.get(digit);
    for (String letter : possibleLetters) {
      
      // Add the current letter to the path
      path.append(letter);
      
      // Recursively explore the next digit
      backtrack(index + 1, path, digits, letters, combinations);
      
      // Remove the current letter from the path before backtracking to explore other combinations
      path.deleteCharAt(path.length() - 1);
    }
  }

  public List<String> letterCombinationsV2(String digits) {
    List<String> combinations = new ArrayList<>();
    
    // If the input is empty, immediately return an empty answer array
    if (digits.isEmpty()) {
      return combinations;
    }
    
    //  Mapping the digits to their corresponding letters
    Map<Character, String[]> digitsMapping = new HashMap<>();
    digitsMapping.put('1', new String[] {""});
    digitsMapping.put('2', new String[] {"a", "b", "c"});
    digitsMapping.put('3', new String[] {"d", "e", "f"});
    digitsMapping.put('4', new String[] {"g", "h", "i"});
    digitsMapping.put('5', new String[] {"j", "k", "l"});
    digitsMapping.put('6', new String[] {"m", "n", "o"});
    digitsMapping.put('7', new String[] {"p", "q", "r", "s"});
    digitsMapping.put('8', new String[] {"t", "u", "v"});
    digitsMapping.put('9', new String[] {"w", "x", "y", "z"});
    
    // Initiate backtracking with an empty path and starting index of 0
    backtrack(0, new StringBuilder(), digits, digitsMapping, combinations);

    return combinations;
  }


}
