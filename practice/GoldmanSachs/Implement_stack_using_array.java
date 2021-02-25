package GoldmanSachs;

public class Implement_stack_using_array {

	int top;
	int arr[] = new int[1000];

	Implement_stack_using_array() {
		top = -1;
	}

	void push(int a) {
		arr[++top] = a;
	}

	/*
	 * The method pop which return the element poped out of the stack
	 */
	int pop() {
		if (top >= 0) {
			return arr[top--];
		} else {
			return -1;
		}
	}
}
