package com.uc.alg.arr;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.Test;

/**
 * @author yangxinyan
 * @date 2020/3/4 13:45
 */
public class SequenceTest {

  int[] arr = {1,10,3,8,23,2,4,0};
  //求最长连续自增步长为1子数组 1,2,3,4
  //int[] arr = {3,8,23,2,4,6,8,10,12,9,11};
//求最长连续自增步长为1子数组8,9,10,11,12

  @Test
  public void test() {
    Map<Integer,String> map = new HashMap<>();
    for(int i : arr) {
      if(map.containsKey(i-1)) {
        map.put(i-1, map.get(i-1)+","+(map.get(i)==null?i:map.get(i)));
      }
      if(map.containsKey(i+1)){
        map.put(i+1, (map.get(i)==null?i:map.get(i))+","+map.get(i+1));
      }

        map.put(i, (map.get(i)==null?i:map.get(i))+"");
    }
    for(Entry<Integer,String> entry : map.entrySet()) {
      System.out.println("===>" + entry.getKey()+"===>" + entry.getValue());
    }
    Arrays.sort(arr);
    Arrays.stream(arr).forEach(t-> System.out.print(t+","));
  }


  @Test
  public void test2() {
    Map<Integer,String> map = new HashMap<>();
    for(int i:arr) {
      map.put(i, i+"");
    }
    Map<Integer,String> map2 = new HashMap<>();
    for(int i:arr) {
      if(map.containsKey(i-1)) {
        map.put(i, map.get(i-1)+"->"+map.get(i));
      }
//      if(map.containsKey(i+1)) {
//        map.put(i, map.get(i)+"->"+map.get(i+1));
//      }
      System.out.println("判断,i:" + i+";;;;;"+map);
    }
    for(Entry<Integer,String> entry : map.entrySet()) {
      System.out.println("===>" + entry.getKey()+"===>" + entry.getValue());
    }

  }



}
