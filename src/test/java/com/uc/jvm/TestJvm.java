package com.uc.jvm;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * Created by yangzhen on 17/8/26.
 */
public class TestJvm {

    public static void main(String[] args) throws Exception {
        //HhClassLoader hh = new HhClassLoader(ClassLoader.getSystemClassLoader().getParent());
        HhClassLoader hh = new HhClassLoader();
//        //Class<?> clasz = hh.loadClass("com.uc.jvm.Goodest");
//        Class<?> clasz = hh.findClass("com.uc.jvm.Goodest");
//        clasz.newInstance();
//        System.out.println(clasz);
//        System.out.println(clasz.getClassLoader());

        System.out.println("-------------------");
        Class<?> class2 = Class.forName("com.uc.jvm.Goodest",true,hh);
        Object o = class2.newInstance();
        System.out.println("o:" + o.getClass().getClassLoader());
        System.out.println(o.getClass().getClassLoader().getParent());
        Thread a = new A();
        a.setContextClassLoader(hh);
        a.start();
    }

    static class A extends Thread {
        @Override
        public void run() {
            System.out.println("------------------");
            Goodest o1 = new Goodest();
            System.out.println("线程:"+o1);
            System.out.println("线程:"+o1.getClass().getClassLoader());
            System.out.println("线程:"+o1.getClass().getClassLoader().getParent());
        }
    }
}
