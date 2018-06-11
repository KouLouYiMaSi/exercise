package com.huo.demos.proxy.inter.istatic;

import com.huo.demos.proxy.inter.SMCompany;

public class SuperStarProxy implements SMCompany {

    private SMCompany star;

    public SuperStarProxy(SMCompany star) {
        super();
        this.star = star;
    }

    public SMCompany getStar() {
        return star;
    }

    public void setStar(SMCompany star) {
        this.star = star;
    }

    @Override
    public void signContract() {
        this.beforeSignContract();
        star.signContract();
        this.afterSignContract();
    }

    private void beforeSignContract() {
        System.out.println("巨星签合同之前先审查下对方的资质。。。");
    }

    private void afterSignContract() {
        System.out.println("巨星签完合同，与对方探讨开演唱会的具体事宜。。。");
    }

}
