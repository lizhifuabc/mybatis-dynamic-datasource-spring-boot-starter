package com.tomato.mybatis.dynamic.datasource.strategy.impl;

import com.tomato.mybatis.dynamic.datasource.constant.ShardingConstants;
import com.tomato.mybatis.dynamic.datasource.properties.DynamicDataSourceProperties;
import com.tomato.mybatis.dynamic.datasource.strategy.DatabaseShardingStrategy;

/**
 * 分库策略 基于hash分库
 *
 * @author lizhifu
 * @since 2023/6/14
 */
public class HashDatabaseStrategy implements DatabaseShardingStrategy<String> {
    private final DynamicDataSourceProperties properties;

    public HashDatabaseStrategy(DynamicDataSourceProperties properties) {
        this.properties = properties;
    }

    @Override
    public String getTargetDatabase(String logicDatabase, String shardingKey) {
        if (shardingKey == null) {
            return logicDatabase;
        }
        int hashCode = shardingKey.hashCode();
        return  String.format(logicDatabase + ShardingConstants.DATASOURCE_SEPARATOR + "%03d", hashCode % properties.getDataSourceNum());
    }
}
