package com.doubao.proxy.DoubaoProxy;

import java.lang.reflect.Method;

public interface DoubaoInvocationHandler {

    public Object invoke(Object DoubaoProxy, Method method, Object[] args)
            throws Throwable;
}
