# Given an integer array A of size N. In the array, you can swap (A[i],A[j]) if the following conditions are satisfied: i < j and i is a divisor of j You are also provided M independent queries. In each query, you are given two integers idx and K.For each query, you are required to output the maximum number you can retrieve at index idx after performing at most K swaps. 
## NOTE 1: Queries are given by a 2-D integer array B of size M * 2 where `B[i][0]` denotes idx and `B[i][1]` denotes K. 
## NOTE 2: Consider array A as 1-based indexed.

### Solution Approach : 

We can observe that if k>=2 then the maximum element of the array is the answer for any index.

Since in one swap we can send the maximum element to index 1 and in another swap to the desired index asked in query.
Also, for K=0, we cannot do any swap so the element at that index will be the answer.

Now, we have to find out the answer when k=1.
We can think that if we are at any ith index then we can swap it with any divisor of i or with any multiple of i.

So we can do that in O(N sqrt(N)).


