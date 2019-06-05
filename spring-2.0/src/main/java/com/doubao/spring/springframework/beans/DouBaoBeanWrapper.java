package com.doubao.spring.springframework.beans;


public class DouBaoBeanWrapper {
    private Object wrappedInstance;
    private Class<?> wrappedClass;

    public DouBaoBeanWrapper(Object wrappedInstance){
        this.wrappedInstance = wrappedInstance;
    }

    public Object getWrappedInstance() {
        return this.wrappedInstance;
    }

    public Class<?> getWrappedClass() {
        return this.wrappedInstance.getClass();
    }
}
