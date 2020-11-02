package com.uc.j8;

import java.util.ArrayList;
import java.util.List;

public class Generics {
    private List<? extends A> list = new ArrayList<>();
    private List<? super A> list1 = new ArrayList<>();

//    public void test(List<String> strs) {
//        System.out.println(strs);
//    }

    public void test(List<Integer> strs) {
        System.out.println(strs);
    }

    static class A {
    }

    public void addA(List<? extends A> t) {
        List<A> lsit = new ArrayList<>();
        //this.list.add(t);
    }

    public A getFirstA() {
        return this.list.remove(0);
    }

    public void addA1(A t) {
        this.list1.add(t);
    }

    public Object getFirstA1() {
        return this.list1.remove(0);
    }
}