package LinkedList;

import java.util.Scanner;

public class LinkedListImpl {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int cases = 0, position = 0, value = 0;
		cases = s.nextInt();
		for (int i = 0; i < cases; i++) {
			char ch = s.next().charAt(0);
			switch (ch) {
			case 'i':
				position = s.nextInt();
				value = s.nextInt();
				insert_node(position, value);
				break;
			case 'd':
				position = s.nextInt();
				delete_node(position);
				break;
			case 'p':
				print_ll();
				System.out.println();
				break;
			default:
				System.out.println("Check Your Input");
			}
		}
	}

	public static class Node {
		int d;
		Node next;

		Node(int d) {
			this.d = d;
			next = null;
		}
	}

	public static Node head = new Node(-1);

	public static void insertAtLast(int d) {
		Node n = new Node(d);
		Node t = head;
		while (t.next != null) {
			t = t.next;
		}
		t.next = n;
	}

	public static void insertAtHead(int d) {
		Node n = new Node(d);
		Node t = head.next;
		n.next = t;
		head.next = n;
	}

	public static void deleteFront() {
		Node n = head.next;
		head.next = n.next;
	}

	public static void deleteLast() {
		Node t = head;
		while (t.next.next != null) {
			t = t.next;
		}
		t.next = null;
	}

	public static int lengthLL() {
		int l = 0;
		Node t = head;
		while (t.next != null) {
			l++;
			t = t.next;
		}
		return l;
	}

	public static void insert_node(int position, int value) {
		// @params position, integer
		// @params value, integer

		if (head.next == null || position == 1) {
			insertAtHead(value);
		} else if (position >= lengthLL()) {
			insertAtLast(value);
		} else {
			// take p-1 jumps
			int jump = 1;
			Node t = head;
			while (jump <= position - 1) {
				t = t.next;
				jump++;
			}
			Node n = new Node(value);
			n.next = t.next;
			t.next = n;
		}

	}

	public static void delete_node(int position) {
		// @params position, integer
		if (head.next == null || position == 1) {
			deleteFront();
		} else if (position >= lengthLL()) {
			deleteLast();
		} else {
			// take p-1 jumps
			int jump = 1;
			Node t = head;
			while (jump <= position - 1) {
				t = t.next;
				jump++;
			}
			Node p = t.next;
			t.next = p.next;
		}
	}

	public static void print_ll() {
		// Output each element followed by a space
//    	System.out.println("HEAD= " + head);
		Node t = head.next;
		while (t != null) {
			System.out.print(t.d + " ");
			t = t.next;
		}
	}

}
