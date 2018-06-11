package com.huo.demos.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class StandardInvocation implements InvocationHandler {

    private Object obj;
    
    StandardInvocation(Object obj){
        this.obj=obj;
    }
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before method excute!");
        Object result = method.invoke(obj, args);
        System.out.println("after method excute!");
        return result;
    }

}