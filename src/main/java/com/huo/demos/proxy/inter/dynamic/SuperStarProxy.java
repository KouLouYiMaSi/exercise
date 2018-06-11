package com.huo.demos.proxy.inter.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class SuperStarProxy implements InvocationHandler {

    private Object target;

    public SuperStarProxy(Object target) {
        super();
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("签约之前审核一下对方资质。。。。");
        method.invoke(target, args);
        System.out.println("签约之后商讨举办演唱会具体事宜。。。。");
        return target;
    }

    public Object getProxy(){
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class<?>[] interfaces = target.getClass().getInterfaces();
        return Proxy.newProxyInstance(loader, interfaces, this);
    }

}
