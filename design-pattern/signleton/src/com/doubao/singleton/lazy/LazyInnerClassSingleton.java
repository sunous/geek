package com.doubao.singleton.lazy;

/**
 * Created by Administrator on 2019/4/29.
 */
//完美的避免了饿汉式的内存浪费和懒汉式的线程安全问题、synchronized性能问题。是最牛X的单例模式
public class LazyInnerClassSingleton {

    private LazyInnerClassSingleton(){
        //防止反射入侵，以免出现多个实例
        if(null != LazyHolder.LAZY){
           throw new RuntimeException("不允许创建多个实例");
        }
    }

    public static LazyInnerClassSingleton  getInstance(){
        //在返回结果前，一定会加载内部类
        return LazyHolder.LAZY;
    }
    //内部类只有在外部类方法被调用的时候才会被加载 ，且调用lazyHolder之前一定会被初始化

    public static class LazyHolder{
       private static final LazyInnerClassSingleton LAZY = new LazyInnerClassSingleton();
       private static final String LAZY1 = "111";


    }
}
