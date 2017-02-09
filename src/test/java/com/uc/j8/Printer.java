package com.uc.j8;

public class Printer {

    public final static Node NODE = new Node();
    final String A = "A";
    final String B = "B";

    public void test(){
        Thread a = new Thread(new Runner(), A);
        Thread b = new Thread(new Runner(), B);
        a.start();
        b.start();
        System.out.println("done");
    }

    class Runner implements Runnable {

        @Override
        public void run() {
            String name  = Thread.currentThread().getName();
            System.out.println("name : " + name);
            if(NODE.holder == null) {
                NODE.holder = name;
            }
            while(true){
                if(NODE.holder.equals(name)){
                    System.out.println("name:" + name + " count:" + ++NODE.i);
                    synchronized (NODE) {
                        if(NODE.holder.equals(A)){
                            NODE.holder = B;
                        } else {
                            NODE.holder = A;
                        }
                    }
                }
                if(NODE.i > 4) {
                    break;
                }
            }
        }
    }


    public static void main(String[] args) {
        Printer p = new Printer();
        p.test();

    }
}




class Node {
    volatile int i;
    volatile String holder;
}