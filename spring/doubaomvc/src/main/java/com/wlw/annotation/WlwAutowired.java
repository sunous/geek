package com.wlw.annotation;

import java.lang.annotation.*;

@Documented
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface WlwAutowired {
    boolean required() default true;
    String value() default "";
}
