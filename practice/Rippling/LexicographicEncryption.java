package Rippling;


/**
 * Implement an encryption algorithm to encrypt the string originalString per the following:
 * <p>
 * Initialize two empty strings: temporaryString and encryptedString.
 * <p>
 * At each step, perform one of the following two operations. Choose the order of operations to produce the lexicographically minimum possible encryptedString.
 * <p>
 * Take the first letter from originalString and append it to the end of temporaryString.
 * <p>
 * Take the last letter from temporaryString and append it to the end of the encryptedString.
 * <p>
 * Example:
 * originalString = "dby"
 * output = "bdy"
 * <p>
 * originalString = "vgxgpu"
 * output = "ggpuxv"
 */
public class LexicographicEncryption {
    public static String encrypt(String original) {
        StringBuilder temp = new StringBuilder();
        StringBuilder encrypted = new StringBuilder();
        int index = 0;

        while (index < original.length()) {
            char nextChar = original.charAt(index);

            // Move from temp to encrypted while top of temp is <= nextChar
            while (temp.length() > 0 &&
                    (index == original.length() || temp.charAt(temp.length() - 1) <= nextChar)) {
                encrypted.append(temp.charAt(temp.length() - 1));
                temp.setLength(temp.length() - 1); // pop last char
            }

            temp.append(nextChar); // move next char from original to temp
            index++;
        }

        // Empty remaining temp
        while (temp.length() > 0) {
            encrypted.append(temp.charAt(temp.length() - 1));
            temp.setLength(temp.length() - 1);
        }

        return encrypted.toString();
    }

    public static void main(String[] args) {
        System.out.println(encrypt("dby"));      // bdy
        System.out.println(encrypt("vgxgpu"));   // ggpuxv
    }
}
