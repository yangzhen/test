package com.uc.renren.service;

public class SimpleWordMatcher implements  PrefixMatcher{

  @Override
  public void test() {

  }

  @Override
  public String call(String str) {
    return "SimpleWordMatcher" + str;
  }
}
