package QuickHelper;

public class CheckCharacterAlphaDigitSpecial {

	public static void main(String[] args) {

		System.out.println(isCapitalLetter('m'));
		System.out.println(isSmallLetter('m'));
		System.out.println(isLetter('@'));
		System.out.println(isDigit('q'));
		System.out.println(isSpecialCharacter('!'));
		
		char c = '9';
		int x = c-'0';
		int y = x+1;
		System.out.println(y);
System.out.println(11%10);
	}

	/*****
	 * ASCII value ranges-
	 * 
	 * For capital alphabets 65 – 90 For small alphabets 97 – 122 For digits 48 – 57
	 * All other cases are Special Characters.
	 * 
	 ***********/

	public static boolean isDigit(char c) {
		return (c >= 48 && c <= 57);
	}

	public static boolean isCapitalLetter(char c) {
		return (c >= 65 && c <= 90);
	}

	public static boolean isSmallLetter(char c) {
		return (c >= 97 && c <= 122);
	}

	public static boolean isLetter(char c) {
		return (isCapitalLetter(c) || isSmallLetter(c));
	}

	public static boolean isSpecialCharacter(char c) {
		return !(isDigit(c) || isLetter(c));
	}

}
