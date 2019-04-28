package com.doubao.proxy.jdk;

import com.doubao.proxy.staticed.Person;
import com.doubao.proxy.staticed.Son;

public class JdkProxyTest {

    public static void main(String[] args) {

        JdkProxy jdkProxy = new JdkProxy();
        Person instance = (Person)jdkProxy.getInstance(new Son());
        System.out.println(instance);
        instance.findLove();
    }
}
