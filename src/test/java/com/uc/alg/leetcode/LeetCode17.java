package com.uc.alg.leetcode;

import com.alibaba.druid.sql.visitor.functions.Char;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 *
 * @author yangxinyan
 * @date 2020/4/15 21:41
 */
public class LeetCode17 {

  private static String[] s2 = {"a","b","c"};
  private static String[] s3 = {"d","e","f"};
  private static String[] s4 = {"g","h","i"};
  private static String[] s5 = {"j","k","l"};
  private static String[] s6 = {"m","n","o"};
  private static String[] s7 = {"p","q","r","s"};
  private static String[] s8 = {"t","u","v"};
  private static String[] s9 = {"x","y","z"};

  private static List<String> list = new ArrayList<>();

  private static Map<String,String[]> map = new HashMap<>();
  static {
    map.put("2", s2);
    map.put("3", s3);
    map.put("4", s4);
    map.put("5", s5);
    map.put("6", s6);
    map.put("7", s7);
    map.put("8", s8);
    map.put("9", s9);
  }

  @Test
  public void test() {
    List<String> list = letterCombinations("23");
    System.out.println(list);
  }

  public List<String> letterCombinations(String digits) {
    combine("", digits);
    return list;
  }

  private void combine(String origin, String next) {
    if(StringUtils.isBlank(next)) {
      list.add(origin);
      return;
    }
    String s = next.substring(0, 1);
    for(String str : map.get(s)) {
      combine(origin+str, next.substring(1));
    }
  }


}
