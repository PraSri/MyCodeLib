package GoldmanSachs;

public class ConvertAGivenNumberToWords {

	// checkout : https://leetcode.com/problems/integer-to-english-words/

	/*
	 * Java program to print a given number in words. The program handles till 9
	 * digits numbers and can be easily extended to 20 digit number
	 */

	// Strings at index 0 is not used, it is to make array
	// indexing simple
	static String one[] = { "", "one ", "two ", "three ", "four ", "five ", "six ", "seven ", "eight ", "nine ", "ten ",
			"eleven ", "twelve ", "thirteen ", "fourteen ", "fifteen ", "sixteen ", "seventeen ", "eighteen ",
			"nineteen " };

	// Strings at index 0 and 1 are not used, they is to
	// make array indexing simple
	static String ten[] = { "", "", "twenty ", "thirty ", "forty ", "fifty ", "sixty ", "seventy ", "eighty ",
			"ninety " };

	// n is 1- or 2-digit number
	static String numToWords(int n, String s) {

		String str = "";
		// if n is more than 19, divide it

		if (n > 19) {
			str += ten[n / 10] + one[n % 10];
		} else {
			str += one[n];
		}

		// if n is non-zero
		if (n != 0) {
			str += s;
		}

		return str;
	}

	// Function to print a given number in words
	static String convertToWords(long n) {
		// stores word representation of given number n
		String out = "";

		// handles digits at ten millions and hundred
		// millions places (if any)
		out += numToWords((int) (n / 10000000), "crore ");

		// handles digits at hundred thousands and one
		// millions places (if any)
		out += numToWords((int) ((n / 100000) % 100), "lakh ");

		// handles digits at thousands and tens thousands
		// places (if any)
		out += numToWords((int) ((n / 1000) % 100), "thousand ");

		// handles digit at hundreds places (if any)
		out += numToWords((int) ((n / 100) % 10), "hundred ");

		if (n > 100 && n % 100 > 0) {
			out += "and ";
		}

		// handles digits at ones and tens places (if any)
		out += numToWords((int) (n % 100), "");

		return out;
	}

	// Leetcode

	private final static String[] LESS_THAN_20 = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
			"Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen",
			"Nineteen" };
	private final static String[] TENS = { "", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy",
			"Eighty", "Ninety" };
	private final static String[] THOUSANDS = { "", "Thousand", "Million", "Billion" };

	public static String numberToWords(int num) {
		System.out.println(num);
		if (num == 0)
			return "Zero";

		int i = 0;
		String words = "";

		while (num > 0) {
			if (num % 1000 != 0) {
//				System.out.println(words);
				words = helper(num % 1000) + THOUSANDS[i] + " " + words;
			}
			num /= 1000;
			i++;
		}

		return words.trim();
	}

	private static String helper(int num) {
		if (num == 0)
			return "";
		else if (num < 20)
			return LESS_THAN_20[num] + " ";
		else if (num < 100)
			return TENS[num / 10] + " " + helper(num % 10);
		else
			return LESS_THAN_20[num / 100] + " Hundred " + helper(num % 100);
	}

	// Driver code
	public static void main(String[] args) {
		// long handles upto 9 digit no
		// change to unsigned long long int to
		// handle more digit number
		long n = 438237764;
		String ip = "4382377649";

		// convert given number in words
//		System.out.printf(convertToWords(n));
//		System.out.println();
		System.out.println(numberToWords((int) n));
	}
}
