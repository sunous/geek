package com.doubao.singleton.lazy;

/**
 * Created by Administrator on 2019/4/27.
 */
public class LazyTest {

    public static void main(String[] args) {

        //当方法被synchronized修饰之后，两个线程同时访问getInstance方法的时候，进入方法，其中一个线程状态为running（运行），
        // 另外一个线程状态为monitor（监听），当running线程执行完之后，另外一个线程状态改为running,继续执行
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(new ExectorThread());
        Thread t2 = new Thread(new ExectorThread());

        t1.start();
        t2.start();

        long end = System.currentTimeMillis();
        System.out.println("总耗时："+(end-start));

    }
}
