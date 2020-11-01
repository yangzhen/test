package com.uc.alg.leetcode;

import java.util.Arrays;

/**
 * @author yangxinyan
 * @date 2020/10/1 09:26
 */
public class Leetcode1011 {

  public int shipWithinDays(int[] weights, int D) {
    int minw = Arrays.stream(weights).max().getAsInt();
    int maxw = Arrays.stream(weights).sum();
    while (minw < maxw) {
      int middle = minw+ (maxw-minw)/2;
      boolean good = isGood(weights, middle,D);
      if(good) {
        maxw = middle;
      }else {
        minw=middle+1;
      }
    }
  return minw;
  }

  public boolean isGood(int[] weights, int cap, int D) {
    int cur = 0;
    for(int we : weights) {
      if(cur+we>cap) {
        D--;
        cur=0;
      }
      cur=cur+we;
    }
    return D>0;
  }
}
