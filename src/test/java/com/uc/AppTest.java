package com.uc;

public class AppTest {
    
    public static String transform(String a) {
        try {
            a +="b";
            Integer.parseInt(a);
            return a;
        } catch (Exception e) {
            a +="c";
            return a;
        } finally {
            a += "d";
            System.out.println("=====" + a);
        }
    }
    
    public static void change(String str) {
        str = "aaaa";
    }
    
    
    static String a= "1";
    
    public static void main(String[] args) {
    	
        int[] arr = new int[25];
        System.out.println(arr[4]);
        
        String str = "1234";
        change(str);
        System.out.println(str);
        
        System.out.println(transform(a));
        a= "a";
        System.out.println(transform(a));
        System.out.println(a);
        
        int x=1,y=2,z=3;
        System.out.println(y+=z--/++x);
    }
}
