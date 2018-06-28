package com.huo.demos.proxy.inter.cglib;

import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Enhancer;

import com.huo.demos.proxy.inter.Liyifeng;

public class Test {
    public static void main(String[] args) {
        StarProxy starProxy = new StarProxy();
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "E:\\\\class");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Liyifeng.class);
        enhancer.setCallback(starProxy);
        Liyifeng liyifeng = (Liyifeng) enhancer.create();
        liyifeng.invokeSign();
    }
}
