package helperimpl;

import java.util.Arrays;

import helper.MyPrint;

public class MyPrintIntegerArray implements MyPrint<Integer> {

	public static void main(String[] args) {
		
		printArray(new int[] {1,2,3});

	}

	@Override
	public void print(Integer[] array) {
		Arrays.stream(array).forEach(i -> System.out.print(i + " , "));
	}

	public static void printArray(int[] array) {
		Integer[] arr = Arrays.stream(array).boxed().toArray(Integer[]::new);
		new MyPrintIntegerArray().print(arr);
	}
}
