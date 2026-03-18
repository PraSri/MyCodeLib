package Stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class MaximumFrequencyStack {

	/*
	 * You are given a matrix A which represent operations of size N x 2. Assume
	 * initially you have a stack-like data structure you have to perform operations
	 * on it.
	 * 
	 * Operations are of two types:
	 * 
	 * 1 x: push an integer x onto the stack and return -1
	 * 
	 * 2 0: remove and return the most frequent element in the stack.
	 * 
	 * If there is a tie for the most frequent element, the element closest to the
	 * top of the stack is removed and returned.
	 * 
	 * A[i][0] describes the type of operation to be performed. A[i][1] describe the
	 * element x or 0 corresponding to the operation performed
	 */

	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> a = new ArrayList<>();
		a.add(new ArrayList<>(Arrays.asList(1, 8)));
		a.add(new ArrayList<>(Arrays.asList(1, 6)));
		a.add(new ArrayList<>(Arrays.asList(2, 0)));
		a.add(new ArrayList<>(Arrays.asList(2, 0)));
		a.add(new ArrayList<>(Arrays.asList(1, 7)));
			System.out.println(solve(a));
	}

	/*
	 * build map for frequency of each element with each push check max frequency
	 * and update it
	 * 
	 * take a map of stack where key-> frequency (integer) value-> stack of elements
	 * <frequency, a stack contains all elements which was pushed frequency times>
	 * example: stack is [5,7,5,7,4,5] map will be : 3 : 5, 2 : 7,5, 1 : 4,7,5,
	 * 
	 */

	static Map<Integer, Integer> freq = new HashMap<>();
	static Map<Integer, Stack<Integer>> stackMap = new HashMap<>();
	static int maxFreq = 0;

	private static int push(int x) {
		int f = freq.getOrDefault(x, 0) + 1;
		freq.put(x, f);
		maxFreq = Math.max(maxFreq, f);
		if (!stackMap.containsKey(f)) {
			stackMap.put(f, new Stack<>());
		}
		stackMap.get(f).push(x);
		return -1;
	}

	private static int pop() {
		int x = stackMap.get(maxFreq).isEmpty() ? -1 : stackMap.get(maxFreq).pop();
		freq.put(x, maxFreq - 1);
		if (!stackMap.isEmpty() && stackMap.containsKey(x) && stackMap.get(x).size() == 0) {
			maxFreq--;
		}
		return x;
	}

	/*
	 * A : [ [1, 4] [2, 0] [1, 9] [2, 0] [1, 6] [1, 6] [2, 0] ]
	 */

	public static ArrayList<Integer> solve(ArrayList<ArrayList<Integer>> A) {
		ArrayList<Integer> res = new ArrayList<>();
		for (ArrayList<Integer> a : A) {
			// a={1,4}
			int typeOfOperation = a.get(0);
			if (typeOfOperation == 1) {
				res.add(push(a.get(1)));
			} else {
				res.add(pop());
			}
		}
		return res;
	}

}
