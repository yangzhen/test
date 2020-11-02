package com.uc.dp;

import java.util.Arrays;
import org.junit.Test;

/**
 * @author yangxinyan
 * @date 2020/3/16 22:20
 */
public class LongestIncreasingSubsequence {

  int[] arr = {1,3,5,20,4,7,4};

  @Test
  public void test() {
    int[] dp = new int[arr.length];
    for(int i=0;i<arr.length;i++) {

      Integer max_val=1;
      for(int j=0;j<i;j++) {
        if(arr[i]>arr[j]) {
          max_val=Integer.max(max_val, dp[j]+1);
        }
      }
      dp[i]=max_val;

    }
    System.out.println(Arrays.toString(dp));
  }

}
