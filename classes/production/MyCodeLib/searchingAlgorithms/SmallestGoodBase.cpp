/*
*TBD implement in JAVA using BigInteger concept
*/

#include <bits/stdc++.h>
#define ull unsigned long long
using namespace std;
ull solve(ull n , int d){
    double tn = (double) n;
    ull r = (ull)(pow(tn,1.0/d)+1);
    ull l = 1;
    while(l<=r){
        ull mid = (l+r)/2;
        ull sum = 1 , cur = 1;
        for(int i = 1;i<=d;i++){
            cur*=mid;
            sum+=cur;
        }
        if(sum==n){
            return mid;
        }
        if(sum>n){
            r = mid -1;
        }else{
            l = mid+1;
        }
    }
    return 0;
}
string sgb(string n){
    ull tn = (ull)stoll(n);
    ull x = 1;
    for(int i = 62;i>=1;i--){
        if((x<<i)<tn){
            ull cur = solve(tn,i);
            if(cur!=0){
                return to_string(cur);
            }
        }
    }
    return to_string(tn-1);
}
int main() {
    cout << sgb("13");
}

