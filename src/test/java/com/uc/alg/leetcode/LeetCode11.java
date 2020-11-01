package com.uc.alg.leetcode;

import org.junit.Test;

/**
 * @author yangxinyan
 * @date 2020/4/22 23:01
 */
public class LeetCode11 {

  public int maxArea(int[] height) {
    int i=0;
    int j=height.length-1;

    int max = 0;

    while ((i<j)) {
      int min = Math.min(height[i], height[j]);
      max = Math.max(min*(j-i), max);
      if(height[i]>height[j]) {
        j--;
      } else {
        i++;
      }
    }
    return max;
  }

  @Test
  public void test() {
    int[] arr = {1,8,6,2,5,4,8,3,7};
    int max = maxArea(arr);
    System.out.println(max);
  }



}
