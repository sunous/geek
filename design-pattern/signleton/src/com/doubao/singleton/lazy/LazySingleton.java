package com.doubao.singleton.lazy;

/**
 * Created by Administrator on 2019/4/27.
 */
public class LazySingleton {

    private static LazySingleton lazy=null;

    private LazySingleton(){}

    //当被用到的时候在去加载，优于恶汉式，但是存在线程安全问题
    public static synchronized LazySingleton getInstance(){
        if (lazy == null) {
            lazy =new LazySingleton();
        }
        return lazy;
    }
}
