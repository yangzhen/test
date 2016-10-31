package com.uc.j8;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.alibaba.druid.sql.visitor.functions.Char;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

public class WhyJ8 {

    private static final Logger logger = LoggerFactory.getLogger(WhyJ8.class);
    List<UserScore> list = new ArrayList<UserScore>();

    public List<UserScore> initList() {
        UserScore score = new UserScore();
        score.setUserId(1);
        score.setCourse("chinese");
        score.setScore(8);
        list.add(score);

        UserScore score2 = new UserScore();
        score2.setUserId(7);
        score2.setCourse("math");
        score2.setScore(3);
        list.add(score2);

        UserScore score3 = new UserScore();
        score3.setUserId(7);
        score3.setCourse("english");
        score3.setScore(5);
        list.add(score3);

        return list;
    }

    @org.junit.Before
    public void before() {
        list = initList();
    }

    @Test
    public void testJ8Com() {
        //list.sort(comparingDouble(UserScore::getScore).re);
        list.sort((o1, o2) -> o1.getScore().compareTo(o2.getScore()));
        list.forEach(t -> System.out.println(t));

        list.sort((UserScore o1, UserScore o2) -> o1.getCourse().compareTo(o2.getCourse()));
        list.forEach(t -> logger.info(t.toString()));
    }

    @Test
    public void testOrigin() {
        Collections.sort(list, new Comparator<UserScore>() {
            @Override
            public int compare(UserScore o1, UserScore o2) {
                return Integer.compare(o1.getScore(), o2.getScore());
            }

        });
        list.forEach(t -> System.out.println(t));
    }

    @Test
    public void testStr() {
        List<String> ls = Arrays.asList("nn", "dd", "aa", "1", "21");
        Collections.sort(ls, String::compareToIgnoreCase);
    }

    @Test
    public void testScore() {
        List ss = list.stream().filter(t -> t.getUserId() > 3).sorted(Comparator.comparingInt(UserScore::getScore)).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(ss));
    }

    @Test
    public void testIntStream() {
        List<Integer> list = IntStream.rangeClosed(1, 100).limit(10).sorted().boxed().collect(Collectors.toList());
        System.out.println(list);
        long su = list.stream().map(r -> {
            System.out.println(r);
            ;
            r = r + 10;
            return r;
        }).count();
        System.out.println("su:" + su);
        List<Integer> aa = list.stream().map(r -> r = r + 10).collect(Collectors.toList());
        System.out.println(aa);

        Integer ab = list.stream().reduce((sum, i) -> {
            sum = sum + i;
            return sum;
        }).get();
        System.out.println(ab);
        List<String> a = Stream.of(Arrays.asList("a", "b"), Arrays.asList("df", "gh")).flatMap(t -> t.stream().map(i -> i.toUpperCase())).collect(Collectors.toList());
        System.out.println(a);
        Integer i = list.stream().reduce(0, Integer::sum);
        System.out.println(i);
        Integer ad = list.stream().reduce(0, (x, y) -> x + y).intValue();
        System.out.println(ad);

    }

    @Test
    public void testHH() {
        Map<Integer,Integer> nn = list.stream().collect(Collectors.groupingBy(p -> p.getUserId(), Collectors.summingInt(p -> p.getScore())));
        System.out.println(nn);
        List<Integer> idList = Arrays.asList(1,2,2,3,0);
        IntSummaryStatistics aa = idList.stream().collect(Collectors.summarizingInt(t->t));
        System.out.println(aa);
        Integer a  = idList.stream().reduce(Integer::sum).get();
        System.out.println(a);
    }
}
