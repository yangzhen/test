package com.uc.alg.leetcode;

import java.util.Arrays;
import java.util.Stack;
import org.junit.Test;

/**
 * 找出数组中每个数右边第一个比它大的元素--时间复杂度o(n)单调栈解法
 * https://blog.csdn.net/smileiam/article/details/88732245
 * @author yangxinyan
 * @date 2020/10/31 14:23
 */
public class ArrayRightMax {

  public int[] findMaxRight(int[] array) {
    int[] res = new int[array.length];
    if(array == null || array.length==0) {
      return res;
    }
    Stack<Integer> stack = new Stack<>();
    for(int index=0; index<array.length;index++) {
      while (!stack.isEmpty() && array[index] > array[stack.peek()]) {
        res[stack.pop()] = array[index];
      }
      stack.push(index);
    }

    while (!stack.isEmpty()) {
      res[stack.pop()] = -1;
    }
    return res;
  }


  @Test
  public void test() {
    /**
     * 如数组A=[1,5,3,6,4,8,9,10] 输出[5, 6, 6, 8, 8, 9, 10, -1]
     *
     * 如数组A=[8, 2, 5, 4, 3, 9, 7, 2, 5] 输出[9, 5, 9, 9, 9, -1, -1, 5, -1]
     */
    int[] arr = {1,5,3,6,4,8,9,10};
    int[] res = findMaxRight(arr);
    System.out.println(Arrays.toString(res));

    int[] arr2 = {8, 2, 5, 4, 3, 9, 7, 2, 5};
    int[] res2 = findMaxRight(arr2);
    System.out.println(Arrays.toString(res2));
  }

}
