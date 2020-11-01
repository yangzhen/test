package com.uc.alg.arr;

import java.util.Arrays;
import org.junit.Test;

/**
 * @author yangxinyan
 * @date 2020/2/6 10:34
 */
public class StrCompressTest {

  @Test
  public void test() {
    char[] chars = {'a','a','b','b','c','c','c','f'};
    int i = compress(chars);
    for(char c : chars) {
      System.out.print("===>" + c);
    }
    System.out.println(i);
    char[] chars2 = {'a','a','b','b','c','c','c','f'};
    for(char c : chars2) {
      System.out.print("===>" + c);
    }
    int j = compress(chars2);
    System.out.println(j);
  }


  public int compress2(char[] chars) {
    int indexAns=0;
    int index=0;
    while (index<chars.length) {
      char charCurrent = chars[index];
      int count=0;
      while (index<chars.length && charCurrent == chars[index]) {
        index++;
        count++;
      }
      chars[indexAns++]=charCurrent;
      if(count!=1) {
        for(char c:Integer.toString(count).toCharArray()) {
          chars[indexAns++]=c;
        }
      }
    }
    return indexAns;
    }























  public int compress(char[] chars) {
    int indexAns=0, index=0;
    while ((index<chars.length)) {
      char c = chars[index];
      int count=0;
      while (index<chars.length && chars[index] == c) {
        index++;
        count++;
      }
      chars[indexAns++]=c;
      if(count!=1) {
        for(char ch : Integer.toString(count).toCharArray()) {
          chars[indexAns++]=ch;
        }
      }
    }
    return indexAns;

  }

}
