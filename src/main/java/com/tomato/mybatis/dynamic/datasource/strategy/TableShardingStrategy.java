package com.tomato.mybatis.dynamic.datasource.strategy;

/**
 * 分表策略
 *
 * @author lizhifu
 * @since 2023/6/14
 */
public interface TableShardingStrategy<T> {
    /**
     * 获取实际表名
     * @param logicTable 逻辑表名
     * @param shardingKey 分表键
     * @return 真实表名
     */
    String getTargetTable(String logicTable, T shardingKey);
}
