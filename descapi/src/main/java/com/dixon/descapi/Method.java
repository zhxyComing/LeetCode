package com.dixon.descapi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Create by: dixon.xu
 * Create on: 2020.07.01
 * Functional desc: 解题方法
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Method {

    // 解法序号
    int index();

    // 说明
    String explanation();

    // 时间复杂度
    String timeComplexity();

    // 空间复杂度
    String spaceComplexity();
}
