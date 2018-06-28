package com.huo.demos.proxy.inter.cglib;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class StarProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println(method.getName());
        if ("signContract".equals(method.getName())) {
            System.out.println(method.getName() + "签约之前审核对方资质。。。。");
            proxy.invokeSuper(obj, args);
            System.out.println(method.getName() + "签约之后商讨举办演唱会具体事宜。。。。");
        }else{
            proxy.invokeSuper(obj, args);
        }
        return obj;
    }

}
