import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class StringCustomSort {

	public static class Pair {
		int i;
		char c;

		public Pair(int i, char c) {
			this.i = i;
			this.c = c;
		}

		@Override
		public String toString() {
			return String.valueOf(i)+c;
		}

	}

	public static void main(String[] args) {
		final String ORDER = "FCBWHJLOAQUXMPVINTKGZERDYS";
		String[] str = { "AA", "FB", "A", "FC" };
//		Arrays.sort(str, new Comparator<String>() {
//
//		    @Override
//		    public int compare(String o1, String o2) {
//		       return ORDER.indexOf(o1) -  ORDER.indexOf(o2) ;
//		    }
//		});
		Arrays.sort(str);

		String real = ORDER;
		char[] ca = real.toCharArray();
		Arrays.sort(ca);

		Map<Pair, Character> m = new HashMap<StringCustomSort.Pair, Character>();
		for (int i = 0; i < ORDER.length(); i++) {
			Pair p = new Pair(i, ca[i]);
			m.put(p, ORDER.charAt(i));
		}

		System.out.println(m);

		System.out.println(Arrays.asList(str));
	}

}
