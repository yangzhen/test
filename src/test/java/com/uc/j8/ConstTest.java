package com.uc.j8;

public class ConstTest {

    private ConstTest () {

    }

    public static void main(String[] args){

        Sub base = new Sub();

    }
}

class Base {

    String namea = "base";

    Base() {
        printName();
    }

    static {
        System.out.println("base static");
    }

    void printName() {
        System.out.println("base pring name:" + namea);
    }
}

class Sub extends Base {

    String name = "sub";

    static {
        System.out.println("sub static");
    }

    Sub (){
        super();
    }
    void printName() {
        System.out.println("sub pring name:" + name);
    }

}



