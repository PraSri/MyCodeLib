package slidingwindow;

public class MinimumWindowSubstring {

    //Input: s = "OUZODYXAZV", t = "XYZ"
    //
    //Output: "YXAZ"

    /***
     * Humko string s ke andar ek minimum size ka window dhoondna hai jisme string t ke saare characters present ho.
     *
     *“Window ko right se expand karo, left se shrink karo —
     * dono pointers sirf aage badhte hain — isi wajah se complexity O(n) hoti hai.”
     * */


        public String minWindow(String s, String t) {

            int n = s.length();
            int c = t.length();

            int[] f = new int[128];
            int start = 0;
            int end = 0;
            int minLen = Integer.MAX_VALUE;
            int minStart = 0;

//            1. Pehle frequency array banaya
            //“Mujhe string t ke kaunse characters chahiye aur kitni quantity mein?”
            for(char ch: t.toCharArray()) {
                f[ch - 'A']++;
            }
//            c ? kitne characters abhi bhi chahiye window ko valid banane ke liye

            while(end < n) {
//                “Right pointer aage badhao, aur jaise hi koi required character mile, bacha hua required count (c) kam kar do.”
                if(f[s.charAt(end) - 'A'] > 0) {
                    c--;
                }
                f[s.charAt(end)-'A']--;
                end++; // expansion
//                “Ab window ke andar t ke saare characters aa gaye hain.”
                while(c==0) {
                    // record stats
                    if(minLen > (end - start)) {
                        minLen = end - start;
                        minStart = start;
                    }
                    // contraction is window
//                    Ab hum try karte hain window ko shrink karne ka:
                    char windowStart = s.charAt(start);
                    f[windowStart - 'A']++;
                    if(f[windowStart - 'A'] > 0) {
                        c++;
                    }
                    // “Ab left side se window ko chhota karne ki koshish karo,
                    //lekin jaise hi koi required character window se bahar chala jaye, window invalid ho jayegi — fir dubara expand karna padega.”
                    start++;
                }
            }

            return minLen==Integer.MAX_VALUE?"":s.substring(minStart, minStart + minLen);
        }
}
