package Stack;

import java.util.Stack;

public class SimplifyDirectoryPath {

	public static void main(String[] args) {

		System.out.println(simplifyPath(
				"/./.././ykt/xhp/nka/eyo/blr/emm/xxm/fuv/bjg/./qbd/./../pir/dhu/./../../wrm/grm/ach/jsy/dic/ggz/smq/mhl/./../yte/hou/ucd/vnn/fpf/cnb/ouf/hqq/upz/akr/./pzo/../llb/./tud/olc/zns/fiv/./eeu/fex/rhi/pnm/../../kke/./eng/bow/uvz/jmz/hwb/./././ids/dwj/aqu/erf/./koz/.."));

	}

	public static String simplifyPath(String A) {

		int n = A.length();

		Stack<String> stack = new Stack<>();

		/* "/a/./b/../../c/" */

		String res = "";

		for (int i = 0; i < n; i++) {
			String s = "";
			while (i < n && A.charAt(i) == '/') {
				i++;
			}
			while (i < n && A.charAt(i) != '/') {
				s = s + A.charAt(i);
				i++;
			}
			if (s.equals("..")) {
				if (!stack.isEmpty()) {
					stack.pop();
				} else {
//					return "/";
				}
			} else if (s.equals(".")) {
				continue;
			} else if (s.length() != 0) {
				stack.push(s);
			}
		}

		Stack<String> t = new Stack<>();
		while (!stack.isEmpty()) {
			t.push(stack.pop());
		}

		res = res + "/";

		StringBuilder sb = new StringBuilder(res);

		while (!t.isEmpty()) {
			if (t.size() != 1) {
				sb.append((t.pop() + "/"));
			} else {
				sb.append(t.pop());
			}
		}

		return sb.toString();

	}

}
