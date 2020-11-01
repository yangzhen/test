package com.uc.alg.leetcode;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 *给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 * 说明:
 *
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 *
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 *
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 *
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 *
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 *
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 *
 * @author yangxinyan
 * @date 2020/7/24 11:18
 */
public class LeetCode10 {


  @Test
  public void test() {
    System.out.println(isMatch("aa","a"));
    System.out.println(isMatch("aa","a*"));
    System.out.println(isMatch("ab",".*"));
    System.out.println(isMatch("aab","c*a*b"));
    System.out.println(isMatch("mississippi","mis*is*p*."));
  }

  @Test
  public void test2() {
    System.out.println(isMatch2("aa","a"));
    System.out.println(isMatch2("aa","a*"));
    System.out.println(isMatch2("ab",".*"));
    System.out.println(isMatch2("aab","c*a*b"));
    System.out.println(isMatch2("mississippi","mis*is*p*."));
  }


  public boolean isMatch(String s, String p) {
    int m = s.length();
    int n = p.length();

    boolean[][] f = new boolean[m + 1][n + 1];
    f[0][0] = true;
    for (int i = 0; i <= m; ++i) {
      for (int j = 1; j <= n; ++j) {
        if (p.charAt(j - 1) == '*') {
          f[i][j] = f[i][j - 2];
          if (matches(s, p, i, j - 1)) {
            f[i][j] = f[i][j] || f[i - 1][j];
          }
        }
        else {
          if (matches(s, p, i, j)) {
            f[i][j] = f[i - 1][j - 1];
          }
        }
      }
    }
    return f[m][n];
  }

  public boolean matches(String s, String p, int i, int j) {
    if (i == 0) {
      return false;
    }
    if (p.charAt(j - 1) == '.') {
      return true;
    }
    return s.charAt(i - 1) == p.charAt(j - 1);
  }



  public boolean isMatch2(String s, String p) {
    if(p.isEmpty()) {
      return s.isEmpty();
    }

    boolean headMatched = !s.isEmpty() && s.charAt(0)==p.charAt(0)
        || p.charAt(0)=='.';
    if(p.length()>=2&&p.charAt(1)=='*') {
      return isMatch2(s,p.substring(2))
          || (headMatched && isMatch2(s.substring(1),p));
    } else if(headMatched){
      return isMatch2(s.substring(1),p.substring(1));
    } else {
      return false;
    }

  }


}
