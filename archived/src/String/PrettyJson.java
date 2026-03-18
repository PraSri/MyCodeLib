package String;

import java.util.ArrayList;

public class PrettyJson {

	public static void main(String[] args) {

		String A = "{A:B,C:{D:E,F:{G:H,I:J}}}";
		for (String s : prettyJSON(A)) {
			System.out.println(s);
		}

	}

	public static String[] prettyJSON(String A) {
		ArrayList<String> res = new ArrayList<>();
		convertUtil(res, A);
		String[] ans = new String[res.size()];
		int i = 0;
		for (String s : res) {
			ans[i] = s;
			i++;
		}
		return ans;
	}

	private static void convertUtil(ArrayList<String> res, String s) {
		if (s == null || s.length() == 0)
			return;
		int n = s.length();
		StringBuilder sb = new StringBuilder();
		int br = 0;
		for (int i = 0; i < n; i++) {
			char c = s.charAt(i);
			if (c == ' ')
				continue;
			sb.append(c);
			// add new line when : 1- urrent char is , or bracket 2- next char is bracket
			if (c == ',' || c == '[' || c == '{' || c == ']' || c == '}') {
				if (c == '{' || c == '[') {
					addNewLine(sb, br);
					br++;
				} else if (c == '}' || c == ']') {
					br--;
					addNewLine(sb, br);
					if (i + 1 < s.length()) {
						char nc = s.charAt(i + 1);
						if (nc == ',') {
							sb.append(nc);
							i++;
						}
					}
				} else {
					addNewLine(sb, br);
				}
				res.add(sb.toString());
				sb.setLength(0);
			} else if (i + 1 < s.length()) {
				char nc = s.charAt(i + 1);
				if (nc == '{' || nc == '[' || nc == '}' || nc == ']') {
					addNewLine(sb, br);
					res.add(sb.toString());
					sb.setLength(0);
				}

			}
		}
	}

	private static void addNewLine(StringBuilder sb, int br) {

		for (int i = 0; i < br; i++) {
			sb.insert(0, '\t');
		}

	}

}
