package com.uc.jvm;

public class Goodest {

    static  int i = 0;


    static
    {
        i=i+1;
        System.out.println("我只是一个人才,-----"+ i+"," + Thread.currentThread().getContextClassLoader());
    }

    public Goodest(){
        System.out.println("coustr 我只是一个人才,-----"+ i+"," + Thread.currentThread().getContextClassLoader());
    }

}  