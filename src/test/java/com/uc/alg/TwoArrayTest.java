package com.uc.alg;

import org.junit.Test;

/**
 * @author yangxinyan
 * @date 2020/4/16 22:55
 */
public class TwoArrayTest {

  @Test
  public void test() {
    int[] arr = {4,6,8,8,9,10,12};
    int k = findFirst(arr, 8);
    System.out.println("result---->"+k);
  }

  public int findFirst(int arr[], int p) {
    int left = 0;
    int right = arr.length-1;
    int middle = left+((right-left)>>1);
    while (left<=right) {
      if(arr[middle]>p) {
        right=middle-1;
      }else if(arr[middle]<p) {
        left=middle+1;
      } else{
        if(middle!=0 && arr[middle-1]!=p) {
          return middle;
        }else {
          right=middle-1;
        }
      }
      middle=(left+right)/2;
      System.out.println("------>" + middle);
    }
    return -1;
  }
}
