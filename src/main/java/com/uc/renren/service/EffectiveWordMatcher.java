package com.uc.renren.service;

public class EffectiveWordMatcher implements PrefixMatcher {

  @Override
  public void test() {

  }

  @Override
  public String call(String str) {
    return "EffectiveWordMatcher" + str;
  }
}
