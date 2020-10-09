package ArrayAndMath;

public class RainWaterTrapped {

	public static void main(String[] args) {

	}

	/**
	 * 
	 * This is O(1) space complexity solution
	 * 
	 * My understanding of this solution is : I am moving from left to right 
	 * keeping track of left max height and right max height 
	 * initially i assigned 1st bar as left max height and last bar as right max height
	 * after that see which max height is smaller go to the smaller bar side and update its max height
	 * if upcoming bar is bigger than max then make it as max and add in diff with curr bar in result.........
	 * 
	 */

	
	public int trap(final int[] heights) {
		if ( heights.length <= 2 ) { return 0; }
		        
		        int left = 0, right = heights.length-1, totalArea = 0;
		        int leftMaxHeight = heights[left], rightMaxHeight = heights[right];
		        
		        while ( left < right ) {
		            if ( heights[left] < heights[right] ) {
		                leftMaxHeight = Math.max(leftMaxHeight, heights[++left]);
		                totalArea += leftMaxHeight-heights[left];
		            } else {
		                rightMaxHeight = Math.max(rightMaxHeight, heights[--right]);
		                totalArea += rightMaxHeight-heights[right];
		            } 
		        }
		        return totalArea;
		    }
}
