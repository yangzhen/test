package com.uc.j8;

import java.util.concurrent.ConcurrentHashMap;
import org.junit.Test;

import java.util.LinkedHashMap;

/**
 * Created by yangzhen on 17/2/4.
 */
public class LinkedHashMapTest {

    @Test
    public void testMap() {
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("b","b");
        map.put("1","1");
        map.put("a","a");
        System.out.println(map);
        System.out.println(map);
    }

    @Test
    public void testConMap() {
        ConcurrentHashMap<String,String> map = new ConcurrentHashMap();
        map.put("aa","123");
        map.put("rb","453");
        map.put("aa","453");
        System.out.println(map);
    }
}
