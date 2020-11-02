package com.uc.alg.arr;

import java.util.Arrays;
import org.junit.Test;

/**
 * https://leetcode-cn.com/problems/trapping-rain-water/solution/jie-yu-shui-by-leetcode/
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 *
 *
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
 *
 * 示例:
 *
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/trapping-rain-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author yangxinyan
 * @date 2020/1/10 10:44
 */
public class TrapTest {

  @Test
  public void test() {
    int [] arr = {0,1,0,2,1,0,1,3,2,1,2,1};
    System.out.println(trap1(arr));
    System.out.println(trap2(arr));
  }


  public int trap1(int[] arr) {
    int rain=0;
    for(int i=0;i<arr.length;i++) {
      int leftMax = 0;
      for(int j=0;j<=i;j++) {
        leftMax=Math.max(leftMax, arr[j]);
      }
      int rightMax =0;
      for(int k=i;k<arr.length;k++) {
        rightMax=Math.max(rightMax, arr[k]);
      }
      rain = rain + Math.min(leftMax, rightMax)-arr[i];
    }
    return rain;
  }

  public int trap2(int[] arr) {
    int leftMax = 0;
    int rightMax = 0;
    int rain=0;
    int[] arrLeft = new int[arr.length];
    int[] arrRight = new int[arr.length];
    for(int i=0;i<arr.length;i++) {
      leftMax = Math.max(leftMax, arr[i]);
      arrLeft[i]=leftMax;
    }
    for(int j=arr.length-1;j>=0;j--) {
      rightMax = Math.max(rightMax, arr[j]);
      arrRight[j]=rightMax;
    }

    for(int i=0;i<arr.length;i++) {
      int minMax = Math.min(arrLeft[i], arrRight[i]);
      rain=rain+minMax-arr[i];
    }
    return rain;
  }

}
