package com.uc.aop;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import com.uc.nio.my.HelloService;
import com.uc.nio.my.HelloServiceImpl;

import java.io.*;

/**
 * Created by yangzhen on 17/3/9.
 */
public class Test {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        HelloServiceImpl helloService = new HelloServiceImpl();
        HelloServiceWrap helloServiceWrap = new HelloServiceWrap(helloService);
        HelloService s = (HelloService) helloServiceWrap.getProxy();
        System.out.println(s.hello("hhh"));

        User user = new User();
        user.setApp("app");
        user.setName("yxy");
        user.setNumber(123);
        user.setUserId(235);

        FileOutputStream fileOutputStream = new FileOutputStream("/Users/yangzhen/logs/a.txt");
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
        outputStream.writeObject(user);


        FileInputStream inputStream = new FileInputStream("/Users/yangzhen/logs/a.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        User user1 = (User) objectInputStream.readObject();
        System.out.println(user1);

    }
}
