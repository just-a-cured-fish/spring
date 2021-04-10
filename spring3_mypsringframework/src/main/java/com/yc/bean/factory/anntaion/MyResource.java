package com.yc.bean.factory.anntaion;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @program: testspring
 * @description:按名称装配
 * @author: HillCheung
 * @create: 2021-04-05 11:31
 */
@Target({TYPE, FIELD, METHOD})
@Retention(RUNTIME)
public @interface MyResource {
    String name() default "";
}
