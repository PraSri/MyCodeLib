package Queue;

public class GasStation {

	public static void main(String[] args) {
	}

	public static int canCompleteCircuit(final int[] A, final int[] B) {
		
		int total = 0;
		
		for(int i = 0;i<A.length;i++) {
			total += A[i]-B[i];
		}
		
		if(total<0) {
			return -1;
		}
		
		int fuel = 0;
		int index = 0;
		
		for(int i = 0;i<A.length;i++) {
			fuel += A[i]-B[i];
			if(fuel<0) {
				index = i+1;
				fuel = 0;
			}
		}
		
		return index;
		
		
	}

}
