package com.uc.j8;

public class VariableLengthParam {
    public void say(String a, String b, Object... c) {
        System.out.println("I'm the first method.");
    }

    public void say(String a, Object... c) {
        System.out.println("I'm the second method.");
    }

    public void say(String a, Object b, Object... c) {
        System.out.println("I'm the third method.");
    }

    public void say(String a, Integer b, Object... c) {
        System.out.println("I'm the fourth method.");
    }

    public void say(Object a, Object b, Object... c) {
        System.out.println("I'm the fifth method.");
    }

    public void say(Object a, Integer b, Object... c) {
        System.out.println("I'm the sixth method.");
    }

    public static void main(String[] args) {
        VariableLengthParam vp = new VariableLengthParam();
        vp.say("test", "aaa"); //1
        vp.say("test", null);//2
        vp.say("test", 1);//4
        vp.say(null, null);//2
        vp.say(null, 1);//6
    }
}