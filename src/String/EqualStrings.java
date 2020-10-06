package TestPackage;

public class EqualStrings {

	public static void main(String[] args) {
		
		System.out.println(solve("tjjgtdrntrjtwwixd","dgwwnigjoipdxiopg"));

	}

	public static int solve(String A, String B) {
		int[] fa = new int[27];
		int[] fb = new int[27];
		
		for(char c : A.toCharArray()) {
			fa[c-'a']++;
		}
		
//		for(int i : fa) {
//			System.out.print(i  + " ");
//		}
		
		for(char c : B.toCharArray()) {
			fa[c-'a']++;
		}
		
//		System.out.println("__________________");
//		
//		for(int i : fa) {
//			System.out.print(i  + " ");
//		}
//		
		for(int i : fa) {
			if(i%2!=0) {
				return 0;
			}
		}
		
		return 1;
		
	}

}
