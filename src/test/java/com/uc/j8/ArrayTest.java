package com.uc.j8;

import java.util.Arrays;

/**
 * @author yangxinyan
 * @date 2019/3/28 22:03
 */
public class ArrayTest {

  private ArrayTest() {

  }

  public static ArrayTest getInstance() {
    return Factory.test;
  }

  private static class Factory{
    private static ArrayTest test = new ArrayTest();
  }


  public static void main(String[] args) {
    ArrayTest test = ArrayTest.getInstance();
    ArrayTest test1 = ArrayTest.getInstance();
    System.out.println(test == test1);

    System.out.println(new Adddddsd());

    Integer[] arr = {1,3,5,7};
    Integer[] arr1 = Arrays.copyOf(arr,4);
    arr1[0]=0;
    System.out.println(Arrays.asList(arr));
    System.out.println(Arrays.asList(arr1));
  }

  public static class Adddddsd {

  }
}


