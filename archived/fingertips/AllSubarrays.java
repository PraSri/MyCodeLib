import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AllSubarrays {
    public static void main(String[] args) {
        int[] arr = {1, 1, 2};
        int ans = allSubarrays(arr);
        System.out.println(ans);
    }

    public static int allSubarrays(int[] arr) {

        int n = arr.length;

        Set<Integer> set = new HashSet<>();
        Set<Integer> curr = new HashSet<>();
        List<List<Integer>> subarrs = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                List<Integer> subarr = new ArrayList<>();
                for (int k = i; k <= j; k++) {
                    subarr.add(arr[k]);
                }
                subarrs.add(subarr);
            }
        }

        System.out.println(subarrs);

        for (List<Integer> sub : subarrs) {
            if (sub.size() == 1) {
                set.add(sub.get(0));
            } else {
                int bitor = sub.get(0);
                for (int i = 1; i < sub.size(); i++) {
                    bitor = bitor | sub.get(i);
                }
                if (bitor == 0) {
                    System.out.println(sub);
                }
                set.add(bitor);
            }

        }

        for (int x : arr) {
            Set<Integer> next = new HashSet<>();
            next.add(x);
            for (int y : curr) {
                next.add(x | y);
            }
            set.addAll(next);
            curr = next;
        }


        System.out.println(set);

        return subarrs.size();

    }
}
