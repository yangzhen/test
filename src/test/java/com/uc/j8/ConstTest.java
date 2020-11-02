package com.uc.j8;

/**
 * 石玉琼 静态代码块，类初始化
 */
public class ConstTest {

    private ConstTest () {

    }

    public static void main(String[] args){

        Base base = new Sub();

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



