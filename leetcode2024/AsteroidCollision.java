package leetcode2024;

import java.util.Stack;

public class AsteroidCollision {

  // https://leetcode.com/problems/asteroid-collision/description/?envType=company&envId=facebook&favoriteSlug=facebook-thirty-days
  public static void main(String[] args) {

//    asteroids = [10,2,-5]

  }

  public static int[] asteroidCollision(int[] asteroids) {

    Stack<Integer> stack = new Stack<>();

    for(int a : asteroids) {
      if(a>0) {
        stack.push(a);
      } else {
        while(!stack.empty() && stack.peek() > 0 && stack.peek() < -a) {
          stack.pop();
        }
        if(stack.empty() || stack.peek() < 0) {
          stack.push(a);
        }
        if(stack.peek() == -a) {
          stack.pop();
        }
      }
    }

    int m = stack.size();
    int[] ans = new int[m];
    int k = 0;
    for (int x : stack) {
      ans[k++] = x;
    }

    return ans;

  }

}
