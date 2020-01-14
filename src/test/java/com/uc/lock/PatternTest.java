package com.uc.lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest {

    public static void main(String[] args) {

        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher matcher = pattern.matcher("123a");
        System.out.println(matcher.find());
        System.out.println(matcher.find());
        System.out.println(matcher.find());
        System.out.println(matcher.find());
        System.out.println(matcher.find());
        if(matcher.find()) {
            System.out.println(matcher.group());
        }

        int i=0;

        AtomicInteger atom = new AtomicInteger();


//        while(i<10000) {
//            new Thread(() -> System.out.println(Thread.currentThread().getName() + "---->" + matcher.find()+"," + atom.incrementAndGet())).start();
//
//            new Thread(() -> System.out.println(Thread.currentThread().getName() + "---->" + matcher.find()+"," + atom.incrementAndGet())).start();
//        }
    }
}
