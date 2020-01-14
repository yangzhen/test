package com.uc;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppTest {

  static {
    URL url = Thread.currentThread().getContextClassLoader()
        .getResource("logback.xml");
    String name = url.getFile();
    System.setProperty("logback.configurationFile", name);
  }

  protected Logger logger = LoggerFactory.getLogger(this.getClass());

  private static final List<String> list = new ArrayList<>();

  public static void main(String[] args) {

    System.out.println(UUID.randomUUID().toString());

    int[] arr = new int[25];
    System.out.println(arr[4]);

    int k = 7;
    int x = 12;
    //x%=(k-k%5);
    System.out.println((x % k) - (k % 5));

    for (int i = 0; i < 10; i++) {
      list.add("a");
    }
    System.out.println(list.hashCode());
    for (int i = 0; i < 10; i++) {
      list.add("a");
    }
    System.out.println(list.hashCode());

    String a = "12345";
    int sum = 0;
    for (int i = 0; i < a.length(); i++) {
      sum = sum * 10 + (a.charAt(i) - '0');
      System.out.println(sum);
    }

    Logger logger = LoggerFactory.getLogger(AppTest.class);
    logger.info("xxxxx");

    List<String> list = Arrays.asList("a,b","c");
    String ids = Joiner.on(",").join(list);
    System.out.println(ids);


  }
}
