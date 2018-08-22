package com.uc.jvm;

public class LockTest {

    private static final Object object= new Object();

    public void test() {

        synchronized (object) {

            try {
                object.wait(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
