package com.uc.j8;

import com.google.common.primitives.Ints;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.junit.Test;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by yangzhen on 16/10/12.
 */
public class StreamTest {

    List<UserScore> list = new ArrayList<UserScore>();

    @Test
    public  void testStream() {
        int sum = list.stream().mapToInt(UserScore::getScore).sum();
        //System.out.println(sum);

        List<String> list = Arrays.asList("a","f","c","c","10");
        //Stream stream = Stream.of("a","b","f","e");

        //IntStream.of(1,2,5,5).forEach(System.out::println);
        IntStream.range(20,23).forEach(System.out::println);
        System.out.println("====");
        IntStream.rangeClosed(12,20).forEach(System.out::println);
        Stream<String> stream = list.stream();
        String[] arr=list.stream().toArray(String[]::new);
        List<String> list1 = list.stream().collect(Collectors.toList());
        Set<String> set = stream.collect(Collectors.toSet());
        System.out.println(set);
        List<String> hh = list.stream().map(t -> t.toUpperCase()).collect(Collectors.toList());
        System.out.println(hh);
        List<Integer> li = Arrays.asList(2,3,5,3);
        List<Integer> ll = li.stream().map(t -> t * t).collect(Collectors.toList());
        System.out.println(ll);

        Stream<List<Integer>> stream1 = Stream.of(Arrays.asList(1,2,4),Arrays.asList(3,2,1),Arrays.asList(4,5,0));
        List<Integer> d = stream1.flatMap((t)->t.stream().map(h->h*h)).collect(Collectors.toList());
        System.out.println(d);
        List<Integer> ss = li.stream().peek(t -> System.out.print(t+",")).collect(Collectors.toList());
        System.out.println(ss);
    }







    @org.junit.Before
    public void before() {
        list = initList();
    }

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


    @Test
    public void testHH() {
        String a = "aa";
        Optional.ofNullable(a).ifPresent(t -> System.out.println(t));
        a = null;
        Optional.ofNullable(a).ifPresent(t -> System.out.println(t));
        String b = Optional.ofNullable(a).orElse("cc");
        System.out.println(b);

        String contact = Stream.of("a","c","d").reduce("",String::concat);
        System.out.println(contact);
        List<Integer> list = Arrays.asList(2,3,-1,4);
        double minValue = list.stream().reduce(0, Integer::min);
        System.out.println(minValue);
        Integer sum = list.stream().reduce(0, Integer::sum);
        System.out.println(sum);
        Integer ssum = Stream.of(12, 3, 2).reduce(Integer::sum).get();
        System.out.println(ssum);
        ssum = Stream.of(12, 3, 2).reduce(0,Integer::sum);
    }


    @Test
    public void testSkip() {
        list.sort((a,b)-> a.getScore().compareTo(b.getScore()));
        list.sort((a,b)-> a.getScore()-b.getScore());
        list.forEach(System.out::println);
        UserScore u = list.stream().skip(1).findFirst().orElse(new UserScore());
        System.out.println(u);
        u = list.stream().max((o1, o2) -> o1.getScore()-o2.getScore()).get();
        System.out.println(u);
    }

    @Test
    public  void testRandom() {
        Random seed = new Random(17);
        Supplier<Integer> random = seed::nextInt;
        List<Integer> ls = Stream.generate(random).limit(10).collect(Collectors.toList());
        System.out.println(ls);

        List<UserScore> list = Stream.generate(new UserBuilder()).limit(10).collect(Collectors.toList());
        System.out.println(list);
         Map<String,List<UserScore>> map = list.stream().collect(Collectors.groupingBy(UserScore::getCourse));
        System.out.println(map);
    }

    private class  UserBuilder implements Supplier<UserScore> {
        Random seek = new Random();
        @Override
        public UserScore get() {
            UserScore userScore = new UserScore();
            userScore.setCourse(seek.nextBoolean()?"chinese":"math");
            userScore.setUserId(seek.nextInt());
            userScore.setScore(seek.nextInt());
            return userScore;
        }
    }
}


