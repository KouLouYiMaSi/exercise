package com.huo.demos.proxy.inter.cglib;

import com.huo.demos.proxy.inter.Liyifeng;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;

public class Test {
    public static void main(String[] args) {
        StarProxy starProxy = new StarProxy();
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "E:\\\\class");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Liyifeng.class);
        enhancer.setCallback(starProxy);
        Liyifeng liyifeng = (Liyifeng) enhancer.create();
        liyifeng.signContract();
    }
}
