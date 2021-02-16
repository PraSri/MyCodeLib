package Important;

import java.util.List;

public class FindMajorityElement implements MajorityElementsTypeQuestions {
	
	public static void main(String[] args) {
		MajorityElementsTypeQuestions ob = new FindMajorityElement();
		int ans = ob.majorityElement(new int[] {3,2});
		System.out.println(ans);
	}

	@Override
	public int majorityElement(int[] nums) {

		int count = 0;

		int major = 0;

		for (int i : nums) {
			if (count == 0) {
				major = i;
			}
			if (i == major) {
				count++;
			} else if (i != major) {
				count--;
			}

		}

		return checkMajorityElement(nums, major) ? major : -1;
	}

	@Override
	public boolean checkMajorityElement(int[] nums, int x) {
		int n = nums.length;
		int v = (int) Math.floor(n / 2);
		int count = 0;
		for (int i : nums) {
			if (i == x)
				count++;
		}
		if (count >= v) {
			return true;
		}
		return false;
	}

	@Override
	public List<Integer> majorityElement_ii(int[] nums) {
//		TODO:
		return null;
	}

}
