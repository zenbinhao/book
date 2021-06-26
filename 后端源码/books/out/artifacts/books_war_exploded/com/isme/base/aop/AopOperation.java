package com.isme.base.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//标记注解用于方法上
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)//可以反射获得注解参数
public @interface AopOperation {
    //定义注解上面的参数
    //是否记录登录日志
    boolean saveLog() default true;
}
