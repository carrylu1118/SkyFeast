package com.sky.annotation;

import com.sky.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，用于标识某个方法需要进行功能字段自动填充处理
 */
@Target(ElementType.METHOD)//指定注解在哪里使用
@Retention(RetentionPolicy.RUNTIME)//指定注解的保留时间，在程序运行时持续有效
public @interface AutoFill {
    OperationType value();
}
