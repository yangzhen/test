package com.uc.j8;

import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yangzhen on 16/12/7.
 */
public class ThreadLocalTest {

    public  static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(new A());
        System.gc();
        System.out.println("=======");
        TreeMap<String,String> map =new TreeMap<>();
        map.put("bb","bb");
        map.put("hh","hh");
        map.put("aa","aa");
        System.out.println(map);

        LinkedHashMap<String,String> l = new LinkedHashMap<>();
        l.put("bb","bb");
        l.put("hh","hh");
        l.put("aa","aa");
        System.out.println(l);
    }

    static  class A implements Runnable {

        @Override
        public void run() {
            ThreadLocal th = new ThreadLocal();
            th.set(new byte[20*1024*1024]);
        }
    }
}
