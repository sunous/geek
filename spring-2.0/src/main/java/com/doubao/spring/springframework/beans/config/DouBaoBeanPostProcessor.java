package com.doubao.spring.springframework.beans.config;

public class DouBaoBeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean,String beanName){
        return bean;
    }
    public Object postProcessAfterInitialization(Object bean,String beanName){
        return bean;
    }
}
