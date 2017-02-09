package com.uc.j8;

/**
 * Created by yangzhen on 17/1/20.
 */
class A {
}

class B extends A {

}
public class CzExtends extends  B {
    public static void main(String[] args) {
        B b = new B();
        CzExtends czExtends = (CzExtends) b;
        System.out.println(czExtends);
    }
}
