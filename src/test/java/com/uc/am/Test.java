package com.uc.am;

public class Test {

	public static void main(String[] args) {
		A a = new A();
		System.out.println(a.get());
	}
}


class A {
	
	public String get() {
		return "xxx";
	}
}