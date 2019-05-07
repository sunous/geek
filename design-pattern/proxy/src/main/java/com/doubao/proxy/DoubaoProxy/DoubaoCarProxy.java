package com.doubao.proxy.DoubaoProxy;

import java.lang.reflect.Method;

public class DoubaoCarProxy implements DoubaoInvocationHandler{

    private Object target;

    public Object getInstance(Object target) throws Exception{
        this.target= target;
        Class<?> clazz = target.getClass();
        return DoubaoProxy.newProxyInstance(new DoubaoClassLoader(), clazz.getInterfaces(), this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
         return method.invoke(this.target, args);
    }
}
