class Solution {
    public void nextPermutation(int[] a) {
        
        // reverse se aisa number dhoono ki aane waala number usse chota ho
        
        int n = a.length; // ex : [2,3,1]
        
        int i = n-1; // i = 2
        
        while(i>0 && a[i-1]>=a[i]) {
            i--; // i = 1
        }
        
        i--; // i = 0 a[0] = 2
        
        // agr waisa nhi milta toh wo last permutation hai aur wo tum sort kodro in ascending order
        
        // agr milta hai, iska matlab i>=0 hai
        
        if(i>=0) {
            
            // abb firse reverse se aisa number serach kro jo just bada ho tumhare upar searched number se i.e just greater than a[i]
            
            int j = n-1;
            
            while(j>=0 && a[j]<=a[i]) {
                j--;
            }
            
            // agr aisa number milta hai toh usko swap krdo ith number se
            
            if(j>=0) {
                swap(a, i, j);
            }
        }
        
        // abb baaki jo i+1 se end tak numbers unko reverse krdo, actually me ye lowest sorting krni thi but reverse bhi same kaam krega, but kyu??  yha pe reverse krne ke baad muhje lowest sorting hi milege
        // above ex: [2,3,1] ki baat kre toh wo swap step ke baad aisa hoga [3,2,1]
        // abb baccha 2,1 reverse krege toh 1,2 milega aur final answer [3,1,2] hoga
        // iska genarailize solution ye keh skte hai ki jo 1st while loop hai i wali usme hum ye ensure kr rhe hai ki left se elements descending order (matlab bade se chota) me ho aur jaise hi ye order break hota hai, hum loop exit kr dete hai, 
        
        reverse(a, i);
        
        
    }
    
    public void swap(int[] a, int i, int j) {
        
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
        
    }
    
    public void reverse(int[] a, int i) {
        int s = i+1;
        int e = a.length - 1;
        while(s<e) {
            if(a[s]>a[e]) {
                swap(a, s, e);
            }
            s++;
            e--;
        }
    }
    
}
