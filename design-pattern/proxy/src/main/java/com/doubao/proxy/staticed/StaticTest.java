package com.doubao.proxy.staticed;

public class StaticTest {

    public static void main(String[] args) {

        Father father = new Father(new Son());
        father.findLove();
    }
}
