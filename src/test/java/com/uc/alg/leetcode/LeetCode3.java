package com.uc.alg.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.Test;

/**
 *
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yangxinyan
 * @date 2020/4/20 22:17
 */
public class LeetCode3 {

  public int lengthOfLongestSubstring(String s) {
    Map<Character,Integer> map = new HashMap<>();
    int max = 0;
    int pos=0;
    for(int i=0;i<s.toCharArray().length; i++) {
      char cc = s.charAt(i);
      if(map.containsKey(cc)) {
          pos = Math.max(map.get(cc), pos);
      }
      max=Math.max(max, i-pos+1);
      map.put(cc, i+1);
    }
    return max;
  }


  public int lengthOfLongestSubstring2(String s) {
   int max =0;
   int i=0,j=0,n=s.length();
   Set<Character> set = new HashSet<>();
   while (i<n && j<n) {
     if(set.contains(s.charAt(j))) {
       set.remove(s.charAt(i));
       max = Math.max(max, j-i);
       i++;
     } else {
       set.add(s.charAt(j++));
       max = Math.max(max, j-i);
     }
   }
   return max;
  }

  @Test
  public void test() {
    int a = lengthOfLongestSubstring2("abcabcbb");
    System.out.println(a);
    int b = lengthOfLongestSubstring2("bbbbb");
    System.out.println(b);
    int c = lengthOfLongestSubstring2("pwwkew");
    System.out.println(c);
    String dd = " ";
    int d = lengthOfLongestSubstring2(dd);
    System.out.println(d);

  }

  public static void main(String[] args) {
    System.out.println(" ".isEmpty()+"," +" ".length());
  }


}
