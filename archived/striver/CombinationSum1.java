import java.util.ArrayList;
import java.util.List;

public class CombinationSum1 {
    public static List<List<Integer>> combinationSum(int[] a, int t) {
        List<List<Integer>> result = new ArrayList<>();
        solve(0, a, t, result, new ArrayList<>());
        return result;
    }

    private static void solve(int index, int[] a, int t, List<List<Integer>> result, List<Integer> temp) {
        // base case
        if (index == a.length) {
            if (t < 0)
                return;
            if (t == 0) {
                result.add(new ArrayList<>(temp));
            }
            return;
        }

        if (a[index] <= t) {
            temp.add(a[index]);
            solve(index, a, t - a[index], result, temp);
            temp.remove(temp.size() - 1);
        }
        solve(index + 1, a, t, result, temp);
    }

    public static void main(String[] args) {
        System.out.println(combinationSum(new int[]{2, 3, 6, 7}, 7));
    }
}
