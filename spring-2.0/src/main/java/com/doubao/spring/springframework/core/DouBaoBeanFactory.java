package com.doubao.spring.springframework.core;

public interface DouBaoBeanFactory {

    /**
     * 根据className获取Bean
     * @param className
     * @return
     */
   Object getBean(Class<?> className);
}
