struct TrieNode {
  TrieNode * children[2];
  int cnt;
};

TrieNode * newNode() {
  TrieNode * temp = new TrieNode;
  temp -> cnt = 0;
  temp -> children[0] = NULL;
  temp -> children[1] = NULL;
  return temp;
}

// check ith bit is set in N
bool check(int N, int i) {
  return (bool)(N & (1 << i));
}


void insert(TrieNode * root, int x) {
  TrieNode * itr = root;

  // padding upto 32 bits 
  for (int i = 31; i >= 0; i--) {
    int f = check(x, i);
    if (!itr -> children[f])// check if itr trie node children[0 or 1] is null or not
      itr -> children[f] = newNode();// if null create new node
    itr = itr -> children[f];// traverse the next children
  }

  itr -> cnt += 1;// increment of cnt for each trie node
}

int findXor(TrieNode * root, int x) {
  TrieNode * itr = root;

  // Do XOR from root to a leaf path 
  int ans = 0;
  for (int i = 31; i >= 0; i--) {
    // Find i-th bit in x 
    int f = check(x, i);

    // Move to the child whose XOR with f 
    // is 1. 
    if ((itr -> children[f ^ 1])) {
      ans = ans + (1 << i); // update answer 
      itr = itr -> children[f ^ 1];
    } else
      itr = itr -> children[f];
  }

  return ans;
}

vector < int > Solution::solve(vector < int > & A) {

  int n = A.size();
  /* make prefix XOR such that ith element represents the xor of all
    elements from 0 to i
    */
  for (int i = 1; i < n; i++) {
    A[i] = A[i] ^ A[i - 1];
  }
  vector < int > ans(2, 1);
  int maxx = A[0];

  TrieNode * root = newNode();
  insert(root, A[0]);

  unordered_map < int, int > mp; // to store the indices of the XOR value
  mp[A[0]] = 0;

  for (int i = 1; i < A.size(); i++) {
    // Case 1 check for subarray(0, i)
    mp[A[i]] = i;
    if (A[i] > maxx) {
      maxx = A[i];
      ans[0] = 1;
      ans[1] = i + 1;
    } else if (A[i] == maxx) {
      if (ans[0] != ans[1]) {
        ans[0] = i + 1;
        ans[1] = i + 1;
      }
    }

    int curMaxx = findXor(root, A[i]);
    int res = curMaxx ^ A[i];
    int j = mp[res];
    j += 1;

    if (curMaxx > maxx) {
      maxx = curMaxx;
      ans[0] = j + 1;
      ans[1] = i + 1;
    } else if (curMaxx == maxx) //  check for minimum length if current maximum  = maxx.           
    {
      int curL = i - j + 1, ansL = ans[1] - ans[0] + 1;
      if (curL < ansL) {
        ans[0] = j + 1;
        ans[1] = i + 1;
      }
    }

    insert(root, A[i]); // insert the xor of the prefix(0, i) into the trie.                  
  }

  return ans;

}