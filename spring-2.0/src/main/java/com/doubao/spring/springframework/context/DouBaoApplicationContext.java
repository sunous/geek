package com.doubao.spring.springframework.context;

import com.doubao.spring.springframework.beans.support.DouBaoBeanDefinitionReader;
import com.doubao.spring.springframework.beans.support.DouBaoDefaultListableBeanFactory;
import com.doubao.spring.springframework.core.DouBaoBeanFactory;

public class DouBaoApplicationContext extends DouBaoDefaultListableBeanFactory implements DouBaoBeanFactory{

    private String[] configLocations;

    private  DouBaoBeanDefinitionReader reader;

    public DouBaoApplicationContext(String... configLocations){
        this.configLocations = configLocations;
    }

    @Override
    public Object getBean(String className) {
        return null;
    }

    @Override
    public void refresh() {
        //1、定位配置文件
        reader = new DouBaoBeanDefinitionReader(this.configLocations);
        //2、加载配置文件，并扫描相关的类，封装到beanDefinition中

        //3、注册，把配置信息放到容器中
    }
}
