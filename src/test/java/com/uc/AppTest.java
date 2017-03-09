package com.uc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AppTest {

	private static final List<String> list = new ArrayList<>();
    
    public static void main(String[] args) {
    	
    	System.out.println(UUID.randomUUID().toString());
    	
        int[] arr = new int[25];
        System.out.println(arr[4]);
        
        int k =7;
        int x = 12;
        //x%=(k-k%5);
        System.out.println((x%k)-(k%5));
        
        for(int i=0;i<10;i++) {
        	list.add("a");
        }
        System.out.println(list.hashCode());
        for(int i=0;i<10;i++) {
        	list.add("a");
        }
        System.out.println(list.hashCode());
        
        String a = "12345";
        int sum = 0;
        for(int i=0;i<a.length();i++) {
        	sum = sum * 10 + (a.charAt(i) - '0');
        	System.out.println(sum);
        }
        
        File file = new File("/Users/yangzhen/logs/test");
        System.out.println(file.exists());
        System.out.println(file.delete());	
    }
}
