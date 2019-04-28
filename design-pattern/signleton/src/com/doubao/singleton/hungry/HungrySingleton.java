package com.doubao.singleton.hungry;

/**
 * Created by Administrator on 2019/4/26.
 */
//恶汉式  在类初始化的时候就被创建了实力
public class HungrySingleton {

    private HungrySingleton(){}

    //final 避免使用反射机制被覆盖了
    private static final HungrySingleton hungry= new HungrySingleton();

    public HungrySingleton getInstance(){
        return hungry;
    }

}
