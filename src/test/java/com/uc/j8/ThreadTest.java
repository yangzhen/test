package com.uc.j8;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * Created by yangzhen on 16/12/5.
 */
public class ThreadTest {

    private CountDownLatch latch = new CountDownLatch(1);

    private static final Logger logger = LoggerFactory.getLogger(ThreadTest.class);


    public static void main(String[] args) {
        //redis-cli -h 123.56.120.157 -p 56379
        C c = new ThreadTest().new C (new Object());
        long time = System.currentTimeMillis();
        c.start();
        System.out.println("========");
        System.out.println(c.get()+","+(System.currentTimeMillis()-time));

    }


    @Test
    public void test() throws ExecutionException, InterruptedException {
        ExecutorService executorService =  Executors.newCachedThreadPool();
        Future<String> a= executorService.submit(new A());
        String hh = a.get();
        System.out.println(hh);
    }

    @Test
    public void testCount() throws InterruptedException {
    	System.out.println("------");
    	ExecutorService executorService = Executors.newCachedThreadPool();
    	executorService.submit(new B());
    	latch.await();
    	System.out.println("=======");
    }

    @Test
    public void testFuture() {
        C c = new C(new Object());
        long time = System.currentTimeMillis();
        c.start();
        System.out.println(c.get()+","+(System.currentTimeMillis()-time));
    }

    class C extends Thread {

        private volatile boolean flag = false;

        private Object object = null;

        String a = null;

        public C(Object object) {
            this.object = object;
        }

        public String get() {

            System.out.println("第一次=========");
            synchronized (object) {
                while(!flag) {
                    try {
                        System.out.println("get====start=====flag:"  + flag);
                        object.wait();
                        System.out.println("get======end===flag:"  + flag);
                    } catch (InterruptedException e) {
                        logger.error("get service error",e);
                    }
                }
            }
            return "ddd";
        }

        @Override
        public void run() {
            synchronized (object) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    logger.error("run service error",e);
                }
                flag = true;
                System.out.println("run=========");
                object.notifyAll();
                //return;
            }
        }

    }


     class B implements Callable<String> {

        @Override
        public String call() throws Exception {
            Thread.sleep(60 * 1000);
            latch.countDown();
            return "aaaa";
        }
    }


    static class A implements Callable<String> {

        @Override
        public String call() throws Exception {
            Thread.sleep(60 * 1000);
            return "aaaa";
        }
    }

}
