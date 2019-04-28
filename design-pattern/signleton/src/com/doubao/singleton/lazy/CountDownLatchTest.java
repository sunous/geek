package com.doubao.singleton.lazy;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2019/4/27.
 */
public class CountDownLatchTest {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(100);
        new Thread(){
            @Override
            public void run() {
                System.out.println( LazySingleton.getInstance());
            }
        };

        try {
            latch.await();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
