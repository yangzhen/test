package com.uc.alg.leetcode;

import java.util.LinkedHashMap;
import org.junit.Test;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 *
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/climbing-stairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yangxinyan
 * @date 2020/4/24 16:24
 */
public class LeetCode70 {

  public int climbStairs(int n) {
    if(n<=2) {
      return n;
    }
    //return climbStairs(n-1) + climbStairs(n-2);
    int[] arr = new int[n];
    return climbStairs2(n,arr);
  }

  public int climbStairs2(int n,int[] arr) {
    if(n<=2) {
      return n;
    }
    if(arr[n-1]==0) {
      return climbStairs2(n-1, arr) + climbStairs2(n-2, arr);
    }
    return arr[n];
  }

  public int climbStairs3(int n) {
    if(n<=2) {
      return n;
    }
    int left=1;
    int right=2;
    int temp=0;
    for(int i=3;i<=n;i++) {
      temp=left+right;
      left=right;
      right=temp;
    }
    return right;
  }


  public int climbStairs4(int n) {
    if(n<=2) {
      return n;
    }
    int[] dp = new int[n+1];
    dp[1]=1;
    dp[2]=2;
    for(int i=3;i<=n;i++) {
      dp[i]=dp[i-1]+dp[i-2];
    }
    return dp[n];
  }



  @Test
  public void test() {
    int t1 = climbStairs3(2);
    System.out.println(t1);
    int t2 = climbStairs3(5);
    System.out.println(t2);
    int t4 = climbStairs4(5);
    System.out.println(t4);

    LinkedHashMap map = new LinkedHashMap();
    map.put("1", "1");
    map.put("2", "2");
    map.put("3", "3");
  }


}
