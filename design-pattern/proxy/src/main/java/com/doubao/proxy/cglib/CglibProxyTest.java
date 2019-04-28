package com.doubao.proxy.cglib;

import com.doubao.proxy.jdk.BenChi;
import com.doubao.proxy.jdk.Car;
import com.doubao.proxy.staticed.Son;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

public class CglibProxyTest {

    public static void main(String[] args) throws Exception{
        CglibProxy cglibProxy = new CglibProxy();
        BenChi instance = (BenChi)cglibProxy.getInstance(new BenChi().getClass());
        System.out.println(instance);


        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy2", new Class[]{Son.class});
        FileOutputStream fs = new FileOutputStream("F://$Proxy2.class");
        fs.write(bytes);
        fs.close();

        //字节码重组的原理

//        Compiler compiler = new Compiler();
    }
}
