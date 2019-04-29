package com.doubao.singleton.Regist;

/**
 * Created by Administrator on 2019/4/29.
 */
public class RegistSingletonTest {

    public static void main(String[] args) {
        new Thread(()-> System.out.println(RegistSingleton.getInstance(RegistSingleton.class.getName()))).start();
        new Thread(()-> System.out.println(RegistSingleton.getInstance(RegistSingleton.class.getName()))).start();

    }
}
