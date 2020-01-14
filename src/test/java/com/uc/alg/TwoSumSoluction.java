package com.uc.alg;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSumSoluction {

  public int[] twoSum1(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
      if (map.containsKey(target - nums[i])) {
        return new int[]{map.get(target - nums[i]), i};
      }
      map.put(nums[i], i);
    }

    return null;
  }

  public int[] twoSum2(int[] nums, int target) {
    int length = nums.length;
    Map<Integer, Integer> map = new HashMap<Integer, Integer>(length);
    for (int i = 0; i < length; i++) {
      map.put(nums[i], i);
    }

    for (int i = 0; i < length; i++) {
      int s = target - nums[i];
      if (map.containsKey(s) && i != map.get(s)) {
        return new int[]{i, map.get(s)};
      }
    }

    return new int[0];
  }

  public static void main(String[] args) {
    TwoSumSoluction solu = new TwoSumSoluction();
    int[] arr = {1,8,7,5,6,8,16};
    int[] sum1 = solu.twoSum1(arr,12);
    System.out.println(sum1[0] + "," + sum1[1]);

    int[] sum2 = solu.twoSum2(arr,12);
    System.out.println(sum2[0] + "," + sum2[1]);

  }


}
