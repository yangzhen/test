package com.uc.j8;

import com.uc.renren.util.UcreditException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yangzhen on 17/1/5.
 */
public class ThreadMTest {

    private static Logger logger = LoggerFactory.getLogger(ThreadMTest.class);

    private  int n;

    private int i=0;

    ThreadMTest() {

    }

    ThreadMTest(int n) {
        this.n=n;
    }

    public void isCheck() {
        System.out.println("xxx");
        if(n!=n) {
            System.out.println("==========not queal");
            throw new UcreditException();
        }
    }

    public static void main(String[] args) {
        final ThreadMTest m  = new ThreadMTest();
        new Thread("thread_A"){

            @Override
            public void run() {
                System.out.println("===thread_A" + m.i);
                synchronized (m) {
                    while(m.i%2==0)  {
                            try {
                                m.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                    }
                    System.out.println("Thread:" + Thread.currentThread().getName()+","+m.i);
                    m.i=m.i+1;
                    m.notifyAll();
                }
                ;
            }
        }.start();
        new Thread("thread_B"){
            @Override
            public void run() {
                System.out.println("===thread_B" + m.i);
                synchronized (m) {
                    while(m.i%2==1)  {
                        if(m.i%2==1) {
                            try {
                                m.wait();
                            } catch (InterruptedException e) {
                                logger.error("thead:" + Thread.currentThread().getName(), e);
                            }
                        }
                    }
                    System.out.println("Thread:" + Thread.currentThread().getName()+","+m.i);
                    m.i=m.i+1;
                    m.notifyAll();
                }
                ;
            }
        }.start();
    }


}
