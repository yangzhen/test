package com.uc.alg;

//leetcode：https://leetcode.com/problems/longest-palindromic-substring/

import org.junit.Test;

public class Palindromic {

  @Test
  public  void testPalind() {
    System.out.println("1221:" + isPalind(1221));
    System.out.println("121:" + isPalind(-121));
    System.out.println("1213:" + isPalind(1213));
  }

  public boolean isPalind(int x) {

    int now=0;
    int remainder=0;
    int original = x;

    while (x!=0) {
      remainder = x % 10;
      now = now *10 + remainder;
      x=x/10;
    }

    return original == now;
  }
}
