package slidingWindow;

public class RepeatedDNASequences {

   public List<String> findRepeatedDnaSequences(String s) {
        
        Set<Integer> words = new HashSet<>();
        Set<Integer> repeatedWords = new HashSet<>();
        List<String> res = new ArrayList<>();

        char[] map = new char[26];
        map[0] = 0; // 00
        map['C'-'A'] = 1; //01
        map['G'-'A'] = 2; // 10
        map['T'-'A'] = 3; // 11

        for (int i = 0; i<s.length()-9; i++) {
           int b = 0;
           for (int j = i;j<i+10;j++){
               b<<=2;
               b |= map[s.charAt(j)-'A'];
           }
           if (!words.add(b) && repeatedWords.add(b)) {
               res.add(s.substring(i, i+10));
           }
        }
        return res;
    }


}
