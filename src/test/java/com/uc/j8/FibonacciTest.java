package com.uc.j8;

public class FibonacciTest {

    public static int fibonacci(int n) {
        if(n==1) {
            return 1;
        }
        if(n==2) {
            return 1;
        }
        return fibonacci(n-1) + fibonacci(n-2);
    }

    public static int fibonacci3(int n){
        switch(n){
            case 1:
                return 1;
            case 2:
                return 1;
            default:
                return fibonacci( n-1)+fibonacci(n-2);
        }
    }


    public static int fibonacci2(int n) {
        if(n==1) {
            return 1;
        }
        if(n==2) {
            return 1;
        }

        int tema=1;
        int temb=1;
        int temc=1;

        for(int i=1;i<=n;i++) {
            if(i>=3) {
                int temp = temb;
                temb=temc;
                tema=temp;
            }
            if(i==1) {
                tema=1;
                continue;
            }
            if(i==2) {
                temb=1;
                continue;
            }
            temc=tema+temb;
        }
        return temc;
    }



    public static void main(String[] args) {
        System.out.println(fibonacci(10));
        System.out.println(fibonacci2(10));
        System.out.println(fibonacci3(10));

    }
}
