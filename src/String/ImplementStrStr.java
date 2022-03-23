package String;

public class ImplementStrStr {

	public static void main(String[] args) {

	}
/*****
	 * locate a substring ( needle ) in a string ( haystack ). Try not to use
	 * standard library string functions for this question. Returns the index of the
	 * first occurrence of needle in haystack, or -1 if needle is not part of
	 * haystack.
	 *****/
	public static int strStr(final String A, final String B) {

		int a = A.length();
		int b = B.length();

		if (a == 0 || b == 0) {
			return -1;
		}

		for (int i = 0; i < a; i++) {
			if (i + b > a)
				break;
			for (int j = 0; j < b; j++) {

				if (A.charAt(i + j) != B.charAt(j)) {
					break;
				}

				if (j == b) {
					return i;
				}

			}
		}

		return -1;

	}
	
	// correct accepted by leetcode
	
    public int strStr(String haystack, String needle) {
        
        if(true) {
            return getIndexOf(haystack, needle);
        }
        
        int big = haystack.length();
        
        int small = needle.length();
       
        if(small == 0){
            return 0;
        }
        
        if(big == 0){
            return -1;
        }
        
        for(int i = 0; i<big; i++) {
            
            if(i+small > big) {
                break;
            }
            
            for(int j = 0; j<small; j++) {
                
                if(haystack.charAt(i+j)!=needle.charAt(j)) {
                    break;
                }
                
                if(j == small-1) {
                    return i;
                }
                
            }
            
            
        }
        
        return -1;
        
    }
    
    public int getIndexOf(String h, String n) {
        
        
        int hl = h.length();
        int nl = n.length();
        
        if(nl>hl){
            return -1;
        }
        
        if(nl==0){
            return 0;
        }
        
        int diff = hl-nl;
        
        for(int i =0 ;i<=diff;i++){
            if(h.substring(i,i+nl).equals(n)) {
                return i;
            }
        }
        
        return -1;
    }

}
