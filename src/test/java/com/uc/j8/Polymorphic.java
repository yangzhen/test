package com.uc.j8;

public class Polymorphic {
    static class Father {
        public void eat() { System.out.println("father"); }
        public static void say() { System.out.println("hello");  }
    }
    static class Son extends Father {
        public void eat() { System.out.println("son"); }
        public static void say() { System.out.println("hi"); }
    }
    public static void main(String[] args) {
        Father f = new Son();
        f.eat();
        f.say(); }
}