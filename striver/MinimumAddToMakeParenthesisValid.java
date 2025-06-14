public class MinimumAddToMakeParenthesisValid {


    public int minAddToMakeValid(String s) {

        int open = 0;
        int closed = 0;

        for (char c : s.toCharArray()) {
            if (c == '(')
                open++;
            else if (open > 0) {
                open--;
            } else
                closed++;
        }

        return open + closed;
    }
}
