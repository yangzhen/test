package com.uc.j8;

/**
 * Created by yangzhen on 17/6/23.
 */
public class TestStr {

    public static String get(String str) {
        String a= "a";
        int i=1;
        try{
            a+="b";
            Integer.parseInt(str);
            i=i+1;
            return a+i;
        } catch (Exception e) {
            a+="c";
            i=i+2;
            return a+i;
        }finally {
            i=i+3;
            a+="d";
        }
    }

    public static void main(String[] args) {
        System.out.println(get("1"));
        System.out.println(get("1a"));

        String a = "helloworld";
        String b = "hello" + "world";
        String c = "hello";
        String d = c + "world";
        String e = new String("helloworld");
        String f = c.intern();
        String g = a.intern();
        System.out.println(a == b);
        System.out.println(a == d);
        System.out.println(a == f);
        System.out.println(f == g);

    }
}
