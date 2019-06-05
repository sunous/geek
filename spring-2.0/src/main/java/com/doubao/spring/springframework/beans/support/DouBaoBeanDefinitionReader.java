package com.doubao.spring.springframework.beans.support;

import com.doubao.spring.springframework.beans.config.DouBaoBeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DouBaoBeanDefinitionReader {

    private static final String SCAN_PACKAGE = "scanPackage";

    private List<String> registryBeanClasses= new ArrayList<>();

    private Properties config = new Properties();

    public DouBaoBeanDefinitionReader(String... configLocations){
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(configLocations[0].replace("classpath:",""));
        try {
            config.load(is);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (null != is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        doScanner(config.getProperty(SCAN_PACKAGE));
    }

    private void doScanner(String scanPackage){
        URL url = this.getClass().getClassLoader().getResource("/"+scanPackage.replaceAll("\\.","/"));
        File fileDir = new File(url.getFile());
        for(File classFile : fileDir.listFiles()){
            if(classFile.isDirectory()) {
                doScanner(scanPackage+"."+classFile.getName());
            }else {
                String className = scanPackage+"."+classFile.getName().replace(".class","");
                registryBeanClasses.add(className);
            }
        }

    }


    public List<DouBaoBeanDefinition> loadBeanDefinitions(){
        List<DouBaoBeanDefinition> beanDefinitions = new ArrayList<>();
        for(String className : registryBeanClasses ){
            if(className.getClass().isInterface())continue;//如果是接口，暂不处理
            //
            beanDefinitions.add(doCreateBeanDefinition(lowerFirstCase(className),className.getClass().getSimpleName()));
            Class<?>[] interfaces = className.getClass().getInterfaces();
            //处理当前类实现的接口
            for(Class clazz : interfaces){
                doCreateBeanDefinition(clazz.getName(),clazz.getSimpleName());
            }
        }
        return beanDefinitions;
    }

    private DouBaoBeanDefinition doCreateBeanDefinition(String beanClassName,String factoryBeanName){
        DouBaoBeanDefinition beanDefinition = new DouBaoBeanDefinition();
        beanDefinition.setBeanClassName(beanClassName);
        beanDefinition.setFactoryBeanName(factoryBeanName);
        return beanDefinition;
    }


    private String lowerFirstCase(String simpleName){

        char[] chars = simpleName.toCharArray();
        if (chars[0]<97){
            chars[0]+=32;//如果小于97.则保证首字母不是小写，可以在这个基础上加上32，变为大写字母
        }
        return String.valueOf(chars);

    }


}
