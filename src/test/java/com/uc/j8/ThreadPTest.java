package com.uc.j8;

/**
 * Created by yangzhen on 17/1/6.
 */
public class ThreadPTest {

    private static  int i=0;

    private static  Thread a = new Thread("A") {
        @Override
        public void run() {
            System.out.println("----------------A");
            while(true) {
                synchronized (this) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("A," + (2*i+1));
                i++;
                if(i>50) {
                    break;
                }
                synchronized (b) {
                    b.notifyAll();
                }
            }

        }
    };


    private  static Thread b = new Thread("B"){
        @Override
        public void run() {
            System.out.println("----------------B");
            while(true) {
                synchronized (this) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("B," + (2*i));
                synchronized (a) {
                    a.notifyAll();
                }
            }

        }
    };


    public static void main(String[] args) throws InterruptedException {
        a.start();
        b.start();
        Thread.sleep(1000);
        synchronized (a) {
            a.notifyAll();
        }
        Thread.sleep(2000);
        System.out.println("===" + i);
    }
}
