package com.uc.alg.leetcode;

import org.junit.Test;

/**
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-change
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yangxinyan
 * @date 2020/4/24 18:06
 */
public class LeetCode322 {

  private static Integer mCost = Integer.MAX_VALUE;

  public int coinChange2(int[] coins, int amount) {
    if(coins.length == 0){
      return -1;
    }
    int[] memo = new int[amount+1];
    memo[0]=0;
    for(int i=1;i<=amount;i++) {
      int min = Integer.MAX_VALUE;
      for(int j=0;j<coins.length;j++) {
        if(i-coins[j]>=0 && memo[i-coins[j]]<min) {
          min=memo[i-coins[j]]+1;
        }
      }
      memo[i]=min;
    }
  return memo[amount]==Integer.MAX_VALUE?-1:memo[amount];
  }

  public int coinChange(int[] coins, int amount) {
    coinChange2(0, coins, amount);
    return mCost;
  }

  public void coinChange2(int count, int[] coins, int amount) {

    if(amount == 0) {
      mCost = Math.min(count, mCost);
    } else if(amount<0) {
      return;
    }

    for(int coin : coins) {
        coinChange2(count+1, coins, amount-coin);
    }
  }


  @Test
  public void test() {
    int[] coins = {6,2,5};
    int amount=11;
    coinChange(coins,amount );
    System.out.println(mCost);
    int res = coinChange2(coins, amount);
    System.out.println(res);
  }


}
