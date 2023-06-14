package com.tomato.mybatis.dynamic.datasource.strategy.impl;

import com.tomato.mybatis.dynamic.datasource.constant.ShardingConstants;
import com.tomato.mybatis.dynamic.datasource.properties.DynamicDataSourceProperties;
import com.tomato.mybatis.dynamic.datasource.strategy.TableShardingStrategy;

/**
 * hash策略
 *
 * @author lizhifu
 * @since 2023/6/141
 */
public class HashTableShardingStrategy implements TableShardingStrategy<String> {
    private final DynamicDataSourceProperties properties;

    public HashTableShardingStrategy(DynamicDataSourceProperties properties) {
        this.properties = properties;
    }

    @Override
    public String getTargetTable(String logicTable, String shardingKey) {
        if (shardingKey != null) {
            int hashCode = shardingKey.hashCode();
            logicTable = String.format(logicTable + ShardingConstants.TABLE_SEPARATOR + "%03d", hashCode % properties.getTableMap().get(logicTable).getTableNum());
        }
        return logicTable;
    }
}
