package com.tomato.mybatis.dynamic.datasource.properties;

import lombok.Data;

/**
 * 分表数据
 *
 * @author lizhifu
 * @since 2023/6/14
 */
@Data
public class DynamicTableDetailProperties {
    /**
     * 表分片数
     */
    private int tableNum;
    /**
     * 分表切分策略
     */
    private String tableShardingStrategy;
    /**
     * 分片键
     */
    private String shardingKey;
}
