package com.doubao.proxy.jdk;


import com.doubao.proxy.staticed.Daughter;
import com.doubao.proxy.staticed.Son;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.Method;

public class JdkProxyTest {

    public static void main(String[] args) throws Exception {

//        JdkProxy jdkProxy = new JdkProxy();
////        Object instance = jdkProxy.getInstance(new Son());
//        Object instance = jdkProxy.getInstance(new Daughter());
//        Method method = instance.getClass().getMethod("findLove");
//        System.out.println(instance);
//        method.invoke(instance);

        Object obj = new JdkProxy().getInstance(new BenChi());
        System.out.println(obj.getClass());
        Method method = obj.getClass().getMethod("getName",null);
        method.invoke(obj);

        //obj.findLove();

        //$Proxy0
//            byte [] bytes = ProxyGenerator.generateProxyClass("$Proxy0",new Class[]{Person.class});
//            FileOutputStream os = new FileOutputStream("E://$Proxy0.class");
//            os.write(bytes);
//            os.close();


        //字节码重组的原理
        //1、拿到被代理对象的调用，并且获取它的所有的接口，反射获取
        //2、jdk proxy类重新生成一个新的类，同时这个新的类要实现被代理类所实现的所有的接口
        //3、把自己新加的业务逻辑方法由一定的逻辑代码去调用
        //4、重新生成对应的java代码.class
        //5、重新加载到JVM中
        //以上就是字节码重组的过程

        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{Car.class});
        FileOutputStream fs = new FileOutputStream("D://$Proxy0.class");
        fs.write(bytes);
        fs.close();

    }
}
