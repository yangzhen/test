package com.uc.j8;

/**
 * @author yangxinyan
 * @date 2019/4/8 14:20
 */
public class SwitchExample {

  public static void switchEx(String content){

    switch (content) {
      case "a":
        System.out.println(65);
      case "b":
        System.out.println(66);
      case "c":
        System.out.println(67);
        default:
          System.out.println("xxx");
    }
  }

  public static void main(String[] args) {
    switchEx("b");
  }

}
