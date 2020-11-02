package com.uc.j8;

import java.util.ArrayList;
import java.util.List;

public class SuperExend {

    static class Food{}

    static class Fruit extends Food{}

    static class Apple extends Fruit{}

    static class RedApple extends Apple{}

    public static void main(String[] args) {
        List<? super Fruit> list = new ArrayList<Fruit>();
        List<? super Fruit> list1 = new ArrayList<Food>();
        //List<? super Fruit> list2 = new ArrayList<RedApple>();  //red error
        Food food = new Food();
        Fruit fruit = new Fruit();
        Apple apple = new Apple();
        RedApple redApple = new RedApple();

        list.add(fruit);
        list.add(apple);
        list.add(redApple);

        Object fruit1 = list.get(0);

        List<? extends Fruit> list3 = new ArrayList<Fruit>();
        List<? extends Fruit> list4 = new ArrayList<Apple>();
        List<? extends Fruit> list5 = new ArrayList<RedApple>();

    }


}
