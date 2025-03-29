package sliding_window;

import java.util.*;

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

   // here we used rolling hash approach, choosen a hash function where collision is minimized & time complexity of hash function is constant - 
   // hash func => H = c1*a^k-1 + ....+ck*a^0
   // for non starting sub string, we used previous hash values, substract H(removed) & added char
   // worst TC can go to O((n-k)*k) for cases like "AAAAAAAAAAAA" but average TC is O(n)

   public static Set<String> findRepeatedSequences(String dna, int k) {
      
        int stringLength = dna.length();

        if (stringLength < k) {
            return new HashSet<String>();
        }

        Map<Character, Integer> mapping = new HashMap<>();
        mapping.put('A', 1);
        mapping.put('C', 2);
        mapping.put('G', 3);
        mapping.put('T', 4);

        int baseValue = 4;

        List<Integer> numbers = new ArrayList<>(Arrays.asList(new Integer[stringLength]));
        Arrays.fill(numbers.toArray(), 0);
        for (int i = 0; i < stringLength; i++) {
            numbers.set(i, mapping.get(dna.charAt(i)));
        }

        int hashValue = 0;

        Set<Integer> hashSet = new HashSet<>();
        Set<String> output = new HashSet<>();

        for (int i = 0; i < stringLength - k + 1; i++) {

            if (i == 0) {
                for (int j = 0; j < k; j++) {
                    hashValue += numbers.get(j) * (int) Math.pow(baseValue, k - j - 1);
                }
            } else {
                int previousHashValue = hashValue;
                hashValue = ((previousHashValue - numbers.get(i - 1) * (int) Math.pow(baseValue, k - 1)) * baseValue) + numbers.get(i + k - 1);
            }

            if (hashSet.contains(hashValue)) {
                output.add(dna.substring(i, i + k));
            }

            hashSet.add(hashValue);
        }
        return output;
      }


}
