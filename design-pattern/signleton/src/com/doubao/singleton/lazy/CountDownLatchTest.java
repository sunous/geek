package com.doubao.singleton.lazy;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2019/4/27.
 */
public class CountDownLatchTest {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(100);
//        new Thread(){
//            @Override
//            public void run() {
//                System.out.println( LazySingleton.getInstance());
//            }
//        };
        long start = System.currentTimeMillis();
        new Thread(()-> System.out.println(LazySingleton.getInstance())).start();
        long end = System.currentTimeMillis();
        System.out.println("共花费时间为："+(end-start));
        try {
            latch.await();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
