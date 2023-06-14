package com.tomato.mybatis.dynamic.datasource.annotation;

import java.lang.annotation.*;

/**
 * 分库分表注解
 *
 * @author lizhifu
 * @since 2023/6/13
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Sharding {
}
