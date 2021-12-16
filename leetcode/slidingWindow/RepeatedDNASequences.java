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
}

