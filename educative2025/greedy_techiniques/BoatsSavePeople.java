package package3;

import java.util.Arrays;

public class BoatsSavePeople {
  // time complexity - O(nlogn)
  // space complexity - O(1)
  public int numRescueBoats(int[] people, int limit) {

    Arrays.sort(people);
    int left = 0;
    int right = people.length - 1;
    int boats = 0;
    while (left <= right) {
      if (people[left] + people[right] <= limit) {
        left++;
      }
      right--;
      boats++;
    }

    return boats;

  }
  
  //We can see the main cost for this logic is the Arrays.sort(). 
  // After seeing the hint "It is guaranteed each person can be carried by a boat", 
  // we know that the number in the array will not be larger than limit; 
  // Hence, it is suitable for bucket sort. Once we sort the array, the remain logic is the same. 
  //The only difference is that we need make sure start and end are pointing to a valid person, since the bucket might be 0;
  // time complexity - O(n)
  // space complexity - O(limit) --> a trafe off with space with improved time complexity

  public int numRescueBoatsWithBucketsort(int[] people, int limit) {
        int[] buckets = new int[limit+1];
        for(int p: people) buckets[p]++;
        int start = 0;
        int end = buckets.length - 1;
        int solution = 0;
        while(start <= end) {
			//make sure the start always point to a valid number
            while(start <= end && buckets[start] <= 0) start++;
			//make sure end always point to valid number
            while(start <= end && buckets[end] <= 0) end--;
			//no one else left on the ship, hence break.
            if(buckets[start] <= 0 && buckets[end] <= 0) break;
            solution++;
            if(start + end <= limit) buckets[start]--; // both start and end can carry on the boat
            buckets[end]--;
        }
        return solution;
    }
}
