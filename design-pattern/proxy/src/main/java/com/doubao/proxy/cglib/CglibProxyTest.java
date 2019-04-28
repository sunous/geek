package com.doubao.proxy.cglib;

import com.doubao.proxy.jdk.BenChi;
import com.doubao.proxy.jdk.Car;

public class CglibProxyTest {

    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        BenChi instance = (BenChi)cglibProxy.getInstance(new BenChi().getClass());
        System.out.println(instance.getName());

//        Compiler compiler = new Compiler();
    }
}
