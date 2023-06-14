package com.tomato.mybatis.dynamic.datasource.constant;

/**
 * 默认常量
 *
 * @author lizhifu
 * @since 2023/6/12
 */
public interface ShardingConstants {
    /**
     * 读写分离
     */
    String MASTER_SLAVE = "MASTER_SLAVE";
    /**
     * 分库分表
     */
    String SHARDING = "SHARDING";
    /**
     * 默认数据源，数据源：主库
     */
    String DEFAULT_MASTER = "master";
    /**
     * 数据源：从库
     */
    String DEFAULT_SLAVE = "slave";
    /**
     * 数据库名分隔符
     */
    String DATASOURCE_SEPARATOR = "_";
    /**
     * 表名分隔符
     */
    String TABLE_SEPARATOR = "_";
}
