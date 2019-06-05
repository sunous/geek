package com.doubao.spring.springframework.context;

import com.doubao.spring.springframework.beans.DouBaoBeanWrapper;
import com.doubao.spring.springframework.beans.config.DouBaoBeanDefinition;
import com.doubao.spring.springframework.beans.config.DouBaoBeanPostProcessor;
import com.doubao.spring.springframework.beans.support.DouBaoBeanDefinitionReader;
import com.doubao.spring.springframework.beans.support.DouBaoDefaultListableBeanFactory;
import com.doubao.spring.springframework.core.DouBaoBeanFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DouBaoApplicationContext extends DouBaoDefaultListableBeanFactory implements DouBaoBeanFactory{

    private String[] configLocations;


    //单例的IOC容器
    private Map<String,Object> singletonObjects = new ConcurrentHashMap<>();

    //通用的IOC容器
    private  Map<String,DouBaoBeanWrapper> factoryBeanInstanceCache = new ConcurrentHashMap<>();

    public DouBaoApplicationContext(String... configLocations){
        this.configLocations = configLocations;
    }

    /**
     * 依赖注入，从这里开始，通过读取beanDefinition中的信息将类实例化
     * 然后通过反射，返回一个类的实例
     * spring的做法是，没有直接返回，而是用beanWrapper将实例包装，然后返回
     * 装饰器模式
     *
     * @param clazz
     * @return
     */
    @Override
    public Object getBean(Class<?> clazz) {
        try{
            return getBean(clazz.getName());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private Object getBean(String beanName) throws Exception{
        DouBaoBeanDefinition beanDefinition = super.beanDefinitionMap.get(beanName);
        DouBaoBeanPostProcessor postProcessor = new DouBaoBeanPostProcessor();
        Object instance = null;
        //bean初始化之前的操作
        postProcessor.postProcessBeforeInitialization(instance,beanName);

        //bean初始化
        instance = instantiateBean(beanName,beanDefinition);


        //bean初始化之后的操作
        postProcessor.postProcessAfterInitialization(instance,beanName);


        DouBaoBeanWrapper DouBaoBeanWrapper  = new DouBaoBeanWrapper(beanDefinition);

        return null;
    }

    private Object instantiateBean(String beanName,DouBaoBeanDefinition beanDefinition) throws Exception {

        String className = beanDefinition.getBeanClassName();
        //反射实例化，得到一个对象
        Object instance;
        //假设是单例，先走流程,如果单例Map中有，则返回单例的实例
        if (this.singletonObjects.containsKey(beanName)){
            instance = this.singletonObjects.get(beanName);
        }else {
            Class<?> clazz = Class.forName(className);
            instance = clazz.newInstance();
            this.singletonObjects.put(beanName,instance);
            this.singletonObjects.put(beanDefinition.getFactoryBeanName(),instance);
        }

        return instance;

    }

    @Override
    public void refresh() throws Exception {
        //1、定位配置文件
        DouBaoBeanDefinitionReader reader = new DouBaoBeanDefinitionReader(this.configLocations);
        //2、加载配置文件，并扫描相关的类，封装到beanDefinition中
        List<DouBaoBeanDefinition> beanDefinitions = reader.loadBeanDefinitions();
        //3、注册，把配置信息放到容器中
        doRegisterBeanDefinition(beanDefinitions);
        //4、不需要延时加载的类，提前初始化
        doAutowired();
    }

    private void doAutowired() {


    }

    private void doRegisterBeanDefinition(List<DouBaoBeanDefinition> beanDefinitions ) throws Exception{
        for (DouBaoBeanDefinition beanDefinition : beanDefinitions) {
            if (super.beanDefinitionMap.containsKey(beanDefinition.getFactoryBeanName())){
                throw new Exception("The "+ beanDefinition.getFactoryBeanName()+" is exist!");
            }
            super.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(),beanDefinition);
        }
    }
}
