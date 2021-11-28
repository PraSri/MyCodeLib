
public class BuildArrayFromPermutation {

	public static void main(String[] args) {

		int[] nums = new int[] { 0, 2, 1, 5, 3, 4 };
		int[] ans = buildArray(nums);

		for (int i = 0; i < nums.length; i++) {
			System.out.print(ans[i] + " ");
		}

	}

	public static int[] buildArray(int[] nums) {
		int n = nums.length;
		int[] ans = new int[nums.length];

		for (int i = 0; i < n; i++) {
			ans[i] = nums[nums[i]];
		}

		return ans;
	}
	
	public static int[] buildArray_in_constant_space_time(int[] nums) {
		int n = nums.length;
		
		
		
		return nums;
	}

}
