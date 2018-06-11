package com.huo.demos.proxy.inter.istatic;

import com.huo.demos.proxy.inter.Luhan;
import com.huo.demos.proxy.inter.SMCompany;

public class SingingCompany {
    public static void main(String[] args) {
        SMCompany luhan = new SuperStarProxy(new Luhan());
        luhan.signContract();
    }
}
