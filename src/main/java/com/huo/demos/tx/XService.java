package com.huo.demos.tx;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class XService {

    public void aMethod(){
        bMethod();
    }

    @Transactional
    public void bMethod(){
        System.out.println("b方法");
    }
}
