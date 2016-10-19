package com.uc.j8;

import org.junit.Test;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.function.Function;

/**
 * Created by yangzhen on 16/10/9.
 */
public class DateTest {

    @Test
    public  void testDate() {
        System.out.println(LocalDateTime.now());
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:MM:ss")));
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        System.out.println(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));

        UserScore userScore = new UserScore();
        userScore.setCourse("aaa");

        UserScore userScore1 = new UserScore();
        userScore1.setCourse("qwe");
        userScore1.setUserId(23);
        userScore1.setScore(8);
        System.out.println(Optional.ofNullable(userScore).filter(t -> t.getCourse().equals("aa")).orElse(userScore1));

        Function<String,Integer> fun = Integer::parseInt;
        System.out.println(fun.apply("12"));

        Function<UserScore,Integer> f = UserScore::getUserId;
        Executors.newCachedThreadPool().execute(()->System.out.println("aa"));
        Executors.newCachedThreadPool().execute(()->System.out.println("aa"));
    }
}
