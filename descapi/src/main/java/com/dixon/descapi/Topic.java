package com.dixon.descapi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Create by: dixon.xu
 * Create on: 2020.07.01
 * Functional desc: 题目描述
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface Topic {

    // 题目序号
    int index();

    // 题目描述
    String topic();

    // 示例
    String example();
}
