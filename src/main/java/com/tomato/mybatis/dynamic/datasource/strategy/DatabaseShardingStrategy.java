package com.tomato.mybatis.dynamic.datasource.strategy;

/**
 * 分表策略
 *
 * @author lizhifu
 * @since 2023/6/14
 */
public interface DatabaseShardingStrategy<D> {
    /**
     * 获取实际库名
     *
     * @param logicDatabase 原始库名(逻辑库名)
     * @param shardingParam 分库key
     * @return 真实库名
     */
    String getTargetDatabase(String logicDatabase, D shardingParam);
}
