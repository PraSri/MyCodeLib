package GoldmanSachs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.PriorityQueue;
import java.util.Scanner;

public class ExternalSorting {

	static class MinHeapNode implements Comparable<MinHeapNode> {
		int element;
		int i; // index of array from which it is picked

		public MinHeapNode(int el, int i1) {
			element = el;
			i = i1;
		}

		public int compareTo(MinHeapNode n) {
			if (element < n.element) {
				return -1;
			} else if (element > n.element) {
				return 1;
			} else {
				return 0;
			}
		}
	}

	static void mergeKSortedFiles(String outputFileName, int k) throws Exception {
		Scanner[] sc = new Scanner[k];
		for (int i = 0; i < k; i++) {
			String fileName = String.format("%d", i);
			sc[i] = new Scanner(new File(fileName));
		}

		MinHeapNode[] minHeapNodeArr = new MinHeapNode[k];
		PriorityQueue<MinHeapNode> minHeap = new PriorityQueue<>();

		int i; // need i for max count, so can break from next while loop
		for (i = 0; i < k; i++) {
			if (!sc[i].hasNext()) {
				break;
			}
			String nextData = sc[i].next();
			minHeapNodeArr[i] = new MinHeapNode(Integer.parseInt(nextData), i);
			minHeap.add(minHeapNodeArr[i]);
		}

		int count = 0;

		// Now one by one get the minimum element from min heap and replace it with the
		// next element
		// run this all filled input files reach EOF
		BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
		while (count != i) {
			MinHeapNode root = minHeap.poll();
			writer.write(root.element + " ");

			if (!sc[root.i].hasNext()) {
				root.element = Integer.MAX_VALUE;
				count++;
			} else {
				root.element = sc[root.i].nextInt();
			}

			minHeap.add(root);
		}

		// close input output files
		for (int j = 0; j < k; j++) {
			sc[j].close();
		}
		writer.close();

	}

	// Using a merge-sort algorithm, create the initial run
	// and divide them evenly among the output files
	static void createIntialRuns(String inputFileName, int run_size, int num_ways) throws Exception {
		Scanner sc = new Scanner(new File(inputFileName));
		BufferedWriter[] writers = new BufferedWriter[num_ways];

		for (int i = 0; i < num_ways; i++) {
			String outputFileName = String.format("%d", i);
			writers[i] = new BufferedWriter(new FileWriter(outputFileName));
		}

		int[] arr = new int[run_size];
		boolean more_input = true;
		int next_output_file = 0;

		int i;

		while (more_input) {
			// write run_size elements into arr from input
			for (i = 0; i < run_size; i++) {
				if (!sc.hasNext()) {
					more_input = false;
					break;
				}

				arr[i] = sc.nextInt();
			}

			mergeSort(arr, 0, i - 1);

			// write the records to the appropriate scratch output file
			// can't assume that the loop runs to run_size
			// since the last run's length may be less than run_size
			for (int j = 0; j < i; j++) {
				writers[next_output_file].write(arr[j] + " ");
			}

			next_output_file++;
		}

		// close input and output files
		for (int j = 0; j < num_ways; j++) {
			writers[j].close();
		}
		sc.close();
	}

	static void mergeSort(int[] arr, int l, int r) {
		if (l < r) {
			int m = (l + r) / 2;
			mergeSort(arr, l, m);
			mergeSort(arr, m + 1, r);

			merge(arr, l, m, r);
		}
	}

	static void merge(int[] arr, int l, int m, int r) {
		// Find sizes of two subarrays to be merged
		int n1 = m - l + 1;
		int n2 = r - m;

		// create temp arrays
		int[] L = new int[n1];
		int[] R = new int[n2];

		// Copy data into temp arrays
		for (int i = 0; i < n1; i++) {
			L[i] = arr[l + i];
		}

		for (int j = 0; j < n2; j++) {
			R[j] = arr[m + 1 + j];
		}

		// merge temp arrays, initialize indices of first and second subarrays
		int i = 0, j = 0;
		int k = l; // initial index of merged subarray

		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				arr[k] = L[i];
				i++;
			} else {
				arr[k] = R[j];
				j++;
			}
			k++;
		}

		// Copy remaining elements of L[] and R[] if any
		while (i < n1) {
			arr[k] = L[i];
			i++;
			k++;
		}

		while (j < n2) {
			arr[k] = R[j];
			j++;
			k++;
		}

	}

	static void externalSort(String inputFile, String outputFile, int num_ways, int run_size) throws Exception {
		createIntialRuns(inputFile, run_size, num_ways);
		mergeKSortedFiles(outputFile, num_ways);
	}

	public static void main(String args[]) throws Exception {
		externalSort("input.txt", "output.txt", 3, 5);
	}
}
