package com.yc.bean.factory.anntaion;

import java.lang.annotation.*;

/**
 * @program: testspring
 * @description:按类型装配
 * @author: HillCheung
 * @create: 2021-04-05 11:31
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAutowired {
    boolean required() default true;
}
