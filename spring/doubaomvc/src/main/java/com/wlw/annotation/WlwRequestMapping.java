package com.wlw.annotation;


import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WlwRequestMapping {

    String value() default "";
}
