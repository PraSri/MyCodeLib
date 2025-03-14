import java.util.ArrayList;
import java.util.List;

public class LetterCasePermutation 
{
    // Function to generate all letter case permutations of the given string
    public static List<String> letterCasePermutation(String s) 
    {
        List<String> result = new ArrayList<>();
        result.add(""); // Initialize result with an empty string

        // Iterate over each character in the input string
        for (char ch : s.toCharArray()) 
        {
            int size = result.size(); // Store current size of result

            // Iterate through all existing permutations
            for (int i = 0; i < size; i++) 
            {
                String str = result.get(i);

                if (Character.isLetter(ch)) 
                {
                    // If the character is a letter, generate both lowercase and uppercase variations
                    result.set(i, str + Character.toLowerCase(ch));
                    result.add(str + Character.toUpperCase(ch));
                } 
                else 
                {
                    // If the character is a digit, simply append it to existing permutations
                    result.set(i, str + ch);
                }
            }
        }
        return result;
    }

    public List<String> letterCasePermutation2(String s) {
        List<String> result = new ArrayList<>();
        backtrack(s.toCharArray(), 0, result);
        return result;
    }

    private void backtrack(char[] arr, int index, List<String> result) {
        if (index == arr.length) {
            result.add(new String(arr));
            return;
        }
        
        // Always explore the current character
        backtrack(arr, index + 1, result);

        // If it's a letter, toggle case and explore
        if (Character.isLetter(arr[index])) {
            arr[index] = Character.isLowerCase(arr[index]) ? 
                         Character.toUpperCase(arr[index]) : 
                         Character.toLowerCase(arr[index]);
            backtrack(arr, index + 1, result);  // Explore toggled case
            arr[index] = Character.isLowerCase(arr[index]) ? 
                         Character.toUpperCase(arr[index]) : 
                         Character.toLowerCase(arr[index]); // Restore original case
        }
    }

    public static void main(String[] args) {
        String[] strings = {"a1b2", "3z4", "ABC", "123", "xYz"}; 

        for (int i = 0; i < strings.length; i++) {
            System.out.println((i + 1) + ".\ts: \"" + strings[i] + "\"");
            List<String> output = letterCasePermutation(strings[i]);

            System.out.print("\n\tOutput: [");
            for (int j = 0; j < output.size(); j++) {
                System.out.print("\"" + output.get(j) + "\"");
                if (j < output.size() - 1) System.out.print(", ");
            }
            System.out.println("]");
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
