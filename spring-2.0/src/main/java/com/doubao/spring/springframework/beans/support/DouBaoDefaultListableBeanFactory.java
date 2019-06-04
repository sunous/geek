package com.doubao.spring.springframework.beans.support;

import com.doubao.spring.springframework.beans.config.DouBaoBeanDefinition;
import com.doubao.spring.springframework.context.support.DouBaoAbstractApplicationContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DouBaoDefaultListableBeanFactory extends DouBaoAbstractApplicationContext{

    //用于存储注册信息的beanDefinition
    protected final Map<String,DouBaoBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

}
