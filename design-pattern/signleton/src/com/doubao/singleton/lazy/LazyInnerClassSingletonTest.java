package com.doubao.singleton.lazy;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2019/4/29.
 */
public class LazyInnerClassSingletonTest {

    public static void main(String[] args) throws Exception{
        long start = System.currentTimeMillis();
        new Thread(()-> System.out.println(LazyInnerClassSingleton.getInstance())).start();
        new Thread(()-> System.out.println(LazyInnerClassSingleton.getInstance())).start();
        long end = System.currentTimeMillis();
        System.out.println("共花费时间为："+(end-start));


        //反射入侵
//        LazyInnerClassSingleton instance = LazyInnerClassSingleton.getInstance();
//        Method[] methods = instance.getClass().getMethods();
//
//        Class<?> aClass = Class.forName(instance.getClass().getName() + "$LazyHolder");
//        Field lazy = aClass.getDeclaredField("LAZY");
//        Field lazy1 = aClass.getDeclaredField("LAZY1");
//        lazy.setAccessible(true);
//        lazy1.setAccessible(true);
//
//        Method method = aClass.getDeclaredMethod("getName");
//        method.invoke(aClass.getDeclaredConstructors()[0].newInstance(aClass.newInstance()));
//        System.out.println(lazy);
//        System.out.println(lazy1);
    }
}
