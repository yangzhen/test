package com.uc.j8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Objectoverriding {

    public static void method(String a) {
        System.out.println("method1...");
    }

    public static void method(String a,String b) {
        System.out.println("method2...");
    }

    public static void method(String a,String b, String ... value) {
        System.out.println("method3...");
    }

    public static void method(String a,String b, Object ... value) {
        System.out.println("method4...");
    }


    public static void method(String a,Integer b) {
        System.out.println("method5...");
    }

    public static void method(String a,Integer b, Integer ... value) {
        System.out.println("method6...");
    }

    public static void method(String a,Integer b, Object ... value) {
        System.out.println("method7...");
    }

    private List<? extends A> list = new ArrayList<>();
    private List<? super A> list1 = new ArrayList<>();

    public static void main(String[] args) {



        method("aa");
        method("aa","b");
        method("aa","b","c");
        method("aa","b",null);
        method("aa","b",(Integer)null);
        method("aa",1);
        method("aa",1,1);
        method("aa",1,(Integer)null);
        method("aa",1,null);


        Map<String,String > map = new HashMap<>();

        Map<Object,Object> map1 = new HashMap<>(map);
    }


}
