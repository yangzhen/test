package com.uc.j8;

import java.util.Arrays;
import java.util.List;

/**
 * @author yangxinyan
 * @date 2018/8/31
 */
public class StrTest {



    public void test1() {
        for(int i=0;i<10;i++) {
            Object o = new Object();
        }
    }

    public void test2() {
        Object o = null;
        for(int i=0;i<10;i++) {
            o = new Object();
        }
    }


    public static void strad() {
        String renrena = "helloworld";
        String renrenc = "hello";
        String renrend = renrenc + "world";

        boolean ad = (renrena == renrend);
    }

    public static void main(String[] args) {

        List<String> list = Arrays.asList("a","b'","c");
        for(int i=0;i<list.size();i++) {
            String str = list.get(i);
            if(str.equals("a")) {
                list.remove(str);
            }
        }
        System.out.println(list);


        String renrena = "helloworld";
        String renrenb = "hello" + "world";

        boolean ab = (renrena == renrenb);

        System.out.println("a == b = " + ab);

        int x=1;
        int y=2;
        int z=3;
        y+=z--/++x;
        System.out.println(y);

        int a=2,b=3,c=4;
        int yu = ++a+b+++c++;
        System.out.println(yu);

    }
}
