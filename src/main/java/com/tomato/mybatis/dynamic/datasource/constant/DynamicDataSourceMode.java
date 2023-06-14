package com.tomato.mybatis.dynamic.datasource.constant;

/**
 * 分库模式
 *
 * @author lizhifu
 * @since 2023/6/14
 */
public enum DynamicDataSourceMode {
    /**
     * 读写分离
     */
    MASTER_SLAVE,
    /**
     * 分库分表
     */
    SHARDING
}
