package com.uc.j8;

/**
 * 计算x的y次方
 * Created by yangzhen on 16/12/15.
 */
public class Cal {

    public static int nsum(int x, int y) {
        return do_nsum(x,y);
    }
    public static int do_nsum(int x, int y) {
        if(y==1) {
            return x;
        }
        if(y==0) {
            return 1;
        }
        int z = y/2;
        int d = do_nsum(x,z);
        d = d*d;
        if(y%2==1) {
            d = d*x;
        }
        return d;
    }

    public static void main(String[] args) {
        System.out.println(5%2);
        System.out.println(nsum(3,3));
    }
}
