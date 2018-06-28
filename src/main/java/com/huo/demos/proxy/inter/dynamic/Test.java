package com.huo.demos.proxy.inter.dynamic;

import com.huo.demos.proxy.inter.Luhan;
import com.huo.demos.proxy.inter.SMCompany;

public class Test {
    public static void main(String[] args) {
        SuperStarProxy ssp = new SuperStarProxy(new Luhan());
        SMCompany luhan = (SMCompany) ssp.getProxy();
        ProxyGeneratorUtils.writeProxyClassToHardDisk("E:\\$SuperStarProxy.class",luhan.getClass().getInterfaces());
        luhan.genStar();
    }
}
