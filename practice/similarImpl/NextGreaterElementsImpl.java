package similarImpl;

import java.util.List;

import ArrayAndMath.NextPermutation;
import Stack.NGE;
import similar.NextGreaterElements;

public class NextGreaterElementsImpl implements NextGreaterElements {

	public static void main(String[] args) {

		System.out.println("HELLO");
		int[] a = new int[] { 11, 13, 21, 3, 4, 2 };
		System.out.println(NGE.nge(a));

	}

	@Override
	public List<Integer> getNextElementInArrayRightOfEachElement(int[] a) {
		return NGE.nge(a);
	}

	@Override
	public int[] nextGreaterElement(int[] nums1, int[] nums2) {
		return null;
	}

	@Override
	public int[] nextGreaterElements(int[] nums) {
		return null;
	}

	@Override
	public int nextGreaterElement(int n) {
		return 0;
	}

	@Override
	public int[] dailyTemperatures(int[] T) {
		return null;
	}

	@Override
	public int[] nextPermutation(int[] nums) {
		return NextPermutation.nextPermutation(nums);
	}

}
