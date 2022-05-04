package ArrayAndMath;

import java.util.Arrays;

public class NextPermutation {

	public static void main(String[] args) {

		int[] a = nextPermutation(new int[] { 5, 3, 4, 9, 7, 6 });
		Arrays.stream(a).forEach(i -> System.out.print(i + ", "));

	}

	/*
	 * Implement the next permutation, which rearranges numbers into the numerically
	 * next greater permutation of numbers for a given array A of size N.
	 * 
	 * If such arrangement is not possible, it must be rearranged as the lowest
	 * possible order i.e., sorted in an ascending order.
	 * 
	 * NOTE:
	 * 
	 * The replacement must be in-place, do not allocate extra memory. DO NOT USE
	 * LIBRARY FUNCTION FOR NEXT PERMUTATION. Use of Library functions will
	 * disqualify your submission retroactively and will give you penalty points.
	 * 
	 * Example : i/p : 2, 3, 1, 5, 4 | o/p : 2, 3, 4, 1, 5
	 * 
	 * i = 4 traverse till i = 2 , i.e. , A[i-1]>=A[i] j = 4 traverse till j = 4 ,
	 * i.e. , A[i]>=A[j] swap values at => (i,j) ...here array will be 2,3,4,5,1
	 * reverse portion of array from i+1 to n-1 now array will be like : 2,3,4,1,5
	 *
	 *
	 *
	 * 
	 * 
	 */

	public static int[] nextPermutation(int[] A) {

		// traverse from rightmost side till u find a digit smaller than previously
		// traversed digit

		int i = A.length - 1;

		while (i > 0 && A[i - 1] >= A[i]) {
			i--;
		}

		i--;

//		 if u don't find such digit that means its sorted in desending order and it is the last permutation...
//		then reverse the digits 

		if (i >= 0) {

			// now search the right side of above found digit and find smallest just greater
			// digit than above found digit

			int j = A.length - 1;

			while (j >= 0 && A[i] >= A[j]) {
				j--;
			}

			// if u able to find such digit then just swap both found digits
			if (j >= 0) {
				swap(A, i, j);
			}
		}

		// reverse the portion of array starting from i+1 to end of array
		reverse(A, i);

		return A;
	}

	private static void swap(int[] nums, int i, int j) {

		int t;

		t = nums[i];
		nums[i] = nums[j];
		nums[j] = t;

	}

	private static void reverse(int[] nums, int i) {

		int right = nums.length - 1;
		int left = i + 1;
		while (left < right) {
			if (nums[left] > nums[right]) {
				swap(nums, right, left);
			}
			left++;
			right--;
		}

	}
	
	/*****************************/
	
	
	class Solution {
    public void nextPermutation(int[] a) {
        
        // reverse se aisa number dhoono ki aane waala number usse chota ho
        
        int n = a.length; // ex : [2,3,1]
        
        int i = n-1; // i = 2
        
        while(i>0 && a[i-1]>=a[i]) {
            i--; // i = 1
        }
        
        i--; // i = 0 a[0] = 2
        
        // agr waisa nhi milta toh wo last permutation hai aur wo tum sort kodro in ascending order
        
        // agr milta hai, iska matlab i>=0 hai
        
        if(i>=0) {
            
            // abb firse reverse se aisa number serach kro jo just bada ho tumhare upar searched number se i.e just greater than a[i]
            
            int j = n-1;
            
            while(j>=0 && a[j]<=a[i]) {
                j--;
            }
            
            // agr aisa number milta hai toh usko swap krdo ith number se
            
            if(j>=0) {
                swap(a, i, j);
            }
        }
        
        // abb baaki jo i+1 se end tak numbers unko reverse krdo, actually me ye lowest sorting krni thi but reverse bhi same kaam krega, but kyu??  yha pe reverse krne ke baad muhje lowest sorting hi milege
        // above ex: [2,3,1] ki baat kre toh wo swap step ke baad aisa hoga [3,2,1]
        // abb baccha 2,1 reverse krege toh 1,2 milega aur final answer [3,1,2] hoga
        // iska genarailize solution ye keh skte hai ki jo 1st while loop hai i wali usme hum ye ensure kr rhe hai ki left se elements descending order (matlab bade se chota) me ho aur jaise hi ye order break hota hai, hum loop exit kr dete hai, 
        
        reverse(a, i);
        
        
    }
    
    public void swap(int[] a, int i, int j) {
        
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
        
    }
    
    public void reverse(int[] a, int i) {
        int s = i+1;
        int e = a.length - 1;
        while(s<e) {
            if(a[s]>a[e]) {
                swap(a, s, e);
            }
            s++;
            e--;
        }
    }
    
}
	

}
