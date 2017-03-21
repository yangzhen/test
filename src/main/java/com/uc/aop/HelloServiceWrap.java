package com.uc.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Objects;

/**
 * Created by yangzhen on 17/3/9.
 */
public class HelloServiceWrap<T> implements InvocationHandler {

    private T target;

    public HelloServiceWrap(T target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("============before");
        System.out.println("i am doing");
        System.out.println("============after");
        return "aaa";
    }

    public  <T> T getProxy() {
        return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),target.getClass().getInterfaces(),this);
    }

}
