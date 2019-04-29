package com.doubao.singleton.Enum;

/**
 * Created by Administrator on 2019/4/29.
 */
public class EnumSingletonTest {

    public static void main(String[] args) {
        EnumSingleton.getInstance().setData(12312312);
        new Thread(()-> System.out.println(EnumSingleton.getInstance().getData())).start();
        new Thread(()-> System.out.println(EnumSingleton.getInstance().getData())).start();
    }
}
