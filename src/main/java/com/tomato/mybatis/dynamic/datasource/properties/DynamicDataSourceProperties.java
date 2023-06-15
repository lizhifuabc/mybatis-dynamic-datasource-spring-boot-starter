package com.tomato.mybatis.dynamic.datasource.properties;

import com.tomato.mybatis.dynamic.datasource.constant.ShardingConstants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * 动态数据源配置
 * @author lizhifu
 */
@ConfigurationProperties(prefix = DynamicDataSourceProperties.PREFIX)
@Data
public class DynamicDataSourceProperties {
    public static final String PREFIX = "spring.datasource.dynamic";
    /**
     * 分库模式
     */
    private String mode = ShardingConstants.SHARDING;
    /**
     * 分库切分策略
     */
    private String databaseShardingStrategy;
    /**
     * 分库数量
     */
    private int dataSourceNum;
    /**
     * 表配置，key:逻辑表名
     */
    private Map<String,DynamicTableDetailProperties> tableMap;
    /**
     * 分片键
     */
    private String shardingKey;
    /**
     * 数据源配置,key:逻辑数据源名
     */
    private Map<String,DynamicDataSourceDetailProperties> datasourceMap;
}
