package com.uc.j8;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class CountDownLatchTest {

    @Test
    public void test() {

        int i = 100;
        CountDownLatch countDownLatch = new CountDownLatch(100);
        AtomicInteger atoc = new AtomicInteger(0);

        while(i>0) {
            new Thread(()->{
                System.out.println("-------------countDown," + atoc.incrementAndGet()+","+ Thread.currentThread().getName()  + "," +System.currentTimeMillis());
                countDownLatch.countDown();
            }).start();
            i--;
        }
        int j=0;
        while(j<50) {
            new Thread(()->{
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("-------------await,"  + atoc.incrementAndGet()+","+ Thread.currentThread().getName()  + "," +System.currentTimeMillis());
            }).start();
            j++;
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-----end," + Thread.currentThread().getName() + "," +System.currentTimeMillis());
    }
}
