package com.doubao.singleton.Regist;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2019/4/29.
 */
public class RegistSingleton {

    private static Map<String,Object> ioc = new ConcurrentHashMap<String,Object>();

    private static RegistSingleton registSingleton = null;
    public static Object getInstance(String className){
        synchronized(ioc){
            try {
                if (registSingleton==null){
                    Class<?> aClass = Class.forName(className);
                    ioc.put(className,aClass);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ioc.get(className);
    }

}
