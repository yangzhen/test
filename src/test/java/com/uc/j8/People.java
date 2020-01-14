package com.uc.j8;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;

public class People {


    public static void main(String[] args) {

        Father father = new Son();
        father.hello();
        father.eat();

        Executors.newCachedThreadPool().execute(()->{ People p = new People();
            System.out.println("--------------");});

        List<String> list = Arrays.asList("a","b","c");
        list.stream().forEach(t-> System.out.println("-----" + t));

    }


     static class Father {

        public static void eat() {
            System.out.println("father eat ....");
        }

        public void hello() {
            System.out.println("father hello ....");
        }
    }


     static class Son extends  Father {

        public static void eat() {
            System.out.println("son eat ....");
        }

        public void hello() {
            System.out.println("son hello ....");
        }
    }


}
