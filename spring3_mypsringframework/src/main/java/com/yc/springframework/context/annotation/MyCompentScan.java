package com.yc.springframework.context.annotation;

import com.yc.springframework.stereotype.MyComponent;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface MyCompentScan {
    Class<?>[] basePackageClasses() default {};

    String [] basePackages() default  "";
}
