package com.uc.j8;

class Log {

  public static String initLog(String log) {
    System.out.println(log);
    return null;
  }
}

/**
 * 基类
 */
class CBase {

  static {
    System.out.println("Base Static Block 1");
  }

  private static String staticValue = Log.initLog("Base Static Fiels");

  static {
    System.out.println("Base Static Block 2");
  }

  {
    System.out.println("Base Normal Block 1");
  }

  private String value = Log.initLog("Base Normal Field");

  {
    System.out.println("Base Normal Block 2");
  }

  CBase() {
    System.out.println("Base Constructor");
  }
}

/**
 * 派生类
 */
public class Derived extends CBase {

  static {
    System.out.println("Static Block 1");
  }

  private static String staticValue = Log.initLog("Static Fiels");

  static {
    System.out.println("Static Block 2");
  }

  {
    System.out.println("Normal Block 1");
  }

  private String value = Log.initLog("Normal Field");

  {
    System.out.println("Normal Block 2");
  }

  Derived() {
    System.out.println("Derived Constructor");
  }

  /**
   * 主线程
   */
  public static void main(String[] args) {
    Derived derived = new Derived();
  }
}
