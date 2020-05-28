package Sorting;

import java.util.Arrays;

public class KthSmallestElementInTheArray {

	public static void main(String[] args) {
	}

	/*
	 * Find the Bth smallest element in given array A .
	 * 
	 * NOTE: Users should try to solve it in <= B swaps.
	 * 
	 */

	public static int kthsmallest(final int[] A, int B) {

		Arrays.sort(A);

		return A[B - 1];

	}

	/*
	 * SOLUTION APPROCH
	 * 
	 * The algorithm of selection sort should be used. Selection sort is a useful
	 * algorithm that uses binary search to get the position of a number in the
	 * array.
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

}

//int Solution::kthsmallest(const vector<int> &A, int B) {
//    int lo=0;
//    int hi=1000000000;
//    int mid,ans;
//    while(lo<=hi)
//    {
//        mid=((hi+lo)/2);
//        int count=0;
//        for(int i=0;i<A.size();i++)
//        {
//            if(A[i]<=mid)
//                count++;
//        }
//        if(count<B)
//        {
//            lo=mid+1;
//        }
//        else
//        {
//            ans=mid;
//            hi=mid-1;
//        }
//        
//    }
//    return ans;
//    
//}
