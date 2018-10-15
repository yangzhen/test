package com.uc.j8;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListTest {

    public static void main(String[] args) {

        Set<UserScore> set = new HashSet<>();

        UserScore u1 = new UserScore();
        System.out.println("u1.hashcode:" + u1.hashCode());
        u1.setUserId(1);
        u1.setScore(80);
        u1.setCourse("语文");
        System.out.println("u1.hashcode:" + u1.hashCode());

        UserScore u2 = new UserScore();
        u2.setUserId(2);
        u2.setScore(90);
        u2.setCourse("数学");
        System.out.println("u2.hashcode:" + u2.hashCode());

        UserScore u3 = new UserScore();
        u3.setUserId(3);
        u3.setScore(100);
        u3.setCourse("英语");
        System.out.println("u3.hashcode:" + u3.hashCode());

        set.add(u1);
        set.add(u2);
        set.add(u3);

        System.out.println("元素数量:" + set.size());

        u3.setScore(200);
        System.out.println("u3.hashcode:" + u3.hashCode());

        set.remove(u3);

        System.out.println("元素数量:" + set.size());

        set.stream().forEach(u -> System.out.println(u));
    }
}
