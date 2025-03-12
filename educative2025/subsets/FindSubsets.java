import java.util.*;
class FindSubsets {

  // checks if the bit at the specified index (bit) in the integer num is 1 or 0
	static int getBit(int num, int bit) {
		int temp = (1 << bit);
		temp = temp & num;
		if (temp == 0) {
			return 0;
		}
		return 1;
	}

	static List<List<Integer>> findAllSubsets(int[] nums) {
    
		List<List<Integer>> setsList = new ArrayList<>();
    
		if (nums.length != 0) {
      
			int subsetsCount = (int) (Math.pow(2, nums.length)); // 2^n, potential subsets
      
			for (int i = 0; i < subsetsCount; ++i) {
        
				List<Integer> subset = new ArrayList<>();
        
				for (int j = 0; j < nums.length; ++j) {
          
					if (getBit(i, j) == 1) {
            
						int x = nums[j];
						subset.add(x);
            
					}
					
				}
        
				setsList.add(subset);
			}
		} else {
			List<Integer> emptySet = new ArrayList<>();
			setsList.add(emptySet);
		}
		return setsList;
	}

    public static void main(String[] args) {
        int[][] inputSets = {
                {},
                {2, 5, 7},
                {1, 2},
                {1, 2, 3, 4},
                {7, 3, 1, 5}
        };

        for (int i = 0; i < inputSets.length; i++) {
            int[] set = inputSets[i];
            System.out.println((i + 1) + ". Set: " + Arrays.toString(set));
            List<List<Integer>> subsets = findAllSubsets(set);
            System.out.println("   Subsets: " + subsets);
			System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
