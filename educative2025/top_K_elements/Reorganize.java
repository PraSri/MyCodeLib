package top_K_elements;

import java.util.*;

class Reorganize {
    public static String reorganizeString(String str) {

        Map <Character, Integer> charCounter = new HashMap <> ();
        for (char c: str.toCharArray()) {
            int freq = charCounter.getOrDefault(c, 0) + 1;
            charCounter.put(c, freq);
        }
        PriorityQueue <Map.Entry <Character, Integer>> maxFreqChar = new PriorityQueue <Map.Entry <Character, Integer>> (
            (item1, item2) -> item2.getValue() - item1.getValue());

        maxFreqChar.addAll(charCounter.entrySet());

        Map.Entry <Character, Integer> previous = null;
        StringBuilder result = new StringBuilder(str.length());
        while (!maxFreqChar.isEmpty() || previous!=null) {

            if (previous != null && maxFreqChar.isEmpty())
                return "";
            
            Map.Entry <Character, Integer> currentEntry = maxFreqChar.poll();
            int count=currentEntry.getValue()-1;
            result.append(currentEntry.getKey());

            if(previous!=null){
                maxFreqChar.add(previous);
                previous=null;
            }
            
            if(count != 0){
                previous = new AbstractMap.SimpleEntry<>(currentEntry.getKey(), count);
               
            }
        }

        return result.toString();
    }

    public static void main(String args[]) {
        String[] inputs = {
            "programming",
            "hello",
            "fofjjb",
            "abbacdde",
            "aba",
            "awesome",
            "aaab",
            "aab"
        };
        for (int i = 0; i < inputs.length; i++) {
            System.out.print(i + 1);
            System.out.println(".\tInput string: \"" + inputs[i] + "\"");

            String output = reorganizeString(inputs[i]);
            output = (output.length() == 0) ? "''" : output;

            System.out.println("\tReorganized string: \"" + output + "\"");
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }

    }
}
