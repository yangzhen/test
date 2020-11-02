package com.uc.dp;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.junit.Test;

/**
 * @author yangxinyan
 * @date 2020/4/6 11:41
 */
public class GoogleSubset {

  public List<Integer> subset(List<Integer> backends, Integer cliendId, Integer subsetSize) {
    int subsetCount = backends.size()/subsetSize;

    int round = cliendId/subsetCount;
    Random random = new Random(round);
    Collections.shuffle(backends, random);

    int subsetId = cliendId%subsetCount;

    int start = subsetId*subsetSize;

    return backends.subList(start, start+subsetSize);
  }

  @Test
  public void test() {
    List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5));

    int clienId = 1;
    int subsetSize = 3;

    Map<Integer,Integer> map = new HashMap<>();
    for(int i=0;i<10;i++) {
      List<Integer> list1 = subset(list, clienId, subsetSize);
      System.out.println("i轮---------->" + list1);
      for(Integer mac : list1) {
        map.put(mac, map.getOrDefault(mac, 0)+1);
      }
    }

    System.out.println("map---------->" + map);
  }

}
