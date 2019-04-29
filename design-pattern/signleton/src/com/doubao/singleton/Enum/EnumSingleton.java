package com.doubao.singleton.Enum;

/**
 * Created by Administrator on 2019/4/29.
 */

//枚举可以看做变量，通常在通用API中使用
public enum  EnumSingleton {
    INSTANCE;

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static EnumSingleton getInstance(){
        return INSTANCE;
    }

}
