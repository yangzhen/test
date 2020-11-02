package com.uc.util;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StrTest {

    public static void aaa(String str) {
        System.out.println("-----str");
    }

//    public static void aaa(Integer integer) {
//        System.out.println("-----integer");
//    }

    public static void aaa(Object obj) {
        System.out.println("-----obj");
    }


    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<String>();
        backtrack(list, "", 0, 0, n);
        return list;
    }

    public void backtrack(List<String> list, String str, int open, int close, int max){

        System.out.println(str + ",open:" + open + ",close:" + close + ",max:" + max);

        if(str.length() == max*2){
            list.add(str);
            return;
        }


        if(open < max)
            backtrack(list, str+"(", open+1, close, max);
        if(close < open)
            backtrack(list, str+")", open, close+1, max);
    }

    public static void main(String[] args) {
        aaa(null);
        List<String> list = new StrTest().generateParenthesis(3);
        System.out.println(Arrays.asList(list));
    }
}
