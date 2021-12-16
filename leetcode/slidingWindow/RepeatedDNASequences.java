package leetcode.slidingWindow;
public class RepeatedDNASequences {
  
  public List<String> findRepeatedDnaSequences(String s) {
        
        Set<String> words = new HashSet<>();
        Set<String> repeatedWords = new HashSet<>();

        for (int i = 0; i<s.length()-9; i++) {
            String word = s.substring(i, i+10);
            if (!words.add(word)) {
                repeatedWords.add(word);
            }
        }

        return new ArrayList<>(repeatedWords);
    }
  
  public List<String> findRepeatedDnaSequencesV2(String s) {

        Set<Integer> words = new HashSet<>();
        Set<Integer> repeatedWords = new HashSet<>();
        List<String> res = new ArrayList<>();

        /**
         *we have only 4 possible letters
         * 0 = 00 (bits in binary number system) = 'A'
         *
         * 1 = 01 (bits in binary number system) = 'C'
         *
         * 2 = 10 (bits in binary number system) = 'G'
         *
         * 3 = 11 (bits in binary number system) = 'T'
         *
         * since there 10 letters and each letter requires only 2 bits,
         * we will need only 10 * 2= 20 bits to code the string
         * (which is less then size of integer in java
         * (as well as in all othere popular languages), which is 4 bytes = 32 bits).
         *
         *
         * */

        char[] map = new char[26];
        map[0] = 0; // 00
        map['C' - 'A'] = 1; //01
        map['G' - 'A'] = 2; // 10
        map['T' - 'A'] = 3; // 11

        for (int i = 0; i < s.length() - 9; i++) {
            int b = 0;
            for (int j = i; j < i + 10; j++) {
                b <<= 2;
                b |= map[s.charAt(j) - 'A'];
            }
            /**
             * For example, this is how "AACCTCCGGT" string will be coded:
             *
             * A A C C T C C G G T
             *
             * 00 00 01 01 11 01 01 10 10 11 = 00000101110101101011 (binary) = 23915 (decimal
             * */
            if (!words.add(b) && repeatedWords.add(b)) {
                res.add(s.substring(i, i + 10));
            }
        }
        return res;
    }
}

