package com.uc.j8;

import java.util.concurrent.Executors;

public class TwoStrThreadTest {

    public static void main(String[] args) {
        Executors.newCachedThreadPool().submit(()->{
            while(true) {
                for(int i=0;i<"hhhhhhh".length();i++) {
                    Thread.sleep(100);
                    System.out.print("hhhhhhh".charAt(i));
                }
                System.out.println();
            }

        });

        Executors.newCachedThreadPool().submit(()->{
            while(true) {

                for(int i=0;i<"aaaaaaaa".length();i++) {
                    Thread.sleep(100);
                    System.out.print("aaaaaaaa".charAt(i));
                }
                System.out.println();
            }

        });
    }
}
