package com.doubao.singleton.lazy;

/**
 * Created by Administrator on 2019/4/27.
 */
public class ExectorThread implements Runnable{


    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+":"+LazySingleton.getInstance());
    }
}
