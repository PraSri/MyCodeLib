package GoldmanSachs;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Rearrange_string_such_that_no_two_adjacent_characters_are_same {

	/****
	 * https://iq.opengenus.org/rearrange-string-no-same-adjacent-characters/
	 * 
	 * Examples Input String: aabc Output String: acba
	 * 
	 * Input String: aaabcab Output String: abacaba
	 * 
	 * Input String: aabbc Output String: aback
	 * 
	 * Input String: aaab Output String: NOT POSSIBLE
	 * 
	 * Input String: aa Output String: NOT POSSIBLE
	 * 
	 * Time complexity of our approach: O(N log N)
	 * 
	 * 
	 */

	// this method rearranges string so two adjacent characters are not same
	static void rearrangeString(String string) {
		int len = string.length();
		// creating an array to store the count of the characters
		int[] characterCount = new int[26];
		// loop to find and set the values of the array
		for (int i = 0; i < len; i++) {
			characterCount[string.charAt(i) - 'a'] += 1;
		}

		PriorityQueue<Key> pQueue = new PriorityQueue<>(new KeyComparator());

		// adding keys to our priority queue if the count of that character is > 0
		for (int i = 'a'; i <= 'z'; i++) {
			if ((characterCount[i - 'a']) > 0) {
				pQueue.add(new Key((char) i, characterCount[i - 'a']));
			}
		}

		string = "";

		Key prev = new Key('#', -1);

		while (pQueue.size() > 0) {
			Key k = pQueue.poll();// this method removes and returns the an element from the head of the queue
			string += k.character;

			if (prev.freq > 0) {
				pQueue.add(prev);
			}

			k.freq -= 1;
			prev = k;
		}

		if (string.length() != len) {
			System.out.println("NOT POSSIBLE");
		} else {
			System.out.println(string);
		}
	}

	public static void main(String[] args) {
		String s = "aabbcadb";
		System.out.println("Expected ababcabd");
		rearrangeString(s);
	}

}

//Creating a class to store our key data
class Key {
	char character;
	int freq;

	Key(char character, int freq) {
		this.character = character;
		this.freq = freq;
	}
}

//Implementing our comparator to compare the keys
class KeyComparator implements Comparator<Key> {
	public int compare(Key key1, Key key2) {
		if (key1.freq < key2.freq) {
			return 1;
		} else if (key1.freq > key2.freq) {
			return -1;
		}
		return 0;
	}
}