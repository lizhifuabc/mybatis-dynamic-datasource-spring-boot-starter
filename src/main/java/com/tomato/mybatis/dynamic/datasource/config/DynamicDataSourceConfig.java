package com.tomato.mybatis.dynamic.datasource.config;

import com.tomato.mybatis.dynamic.datasource.DynamicRoutingDataSource;
import com.tomato.mybatis.dynamic.datasource.constant.ShardingConstants;
import com.tomato.mybatis.dynamic.datasource.context.DynamicDataSourceContextHolder;
import com.tomato.mybatis.dynamic.datasource.properties.DynamicDataSourceDetailProperties;
import com.tomato.mybatis.dynamic.datasource.properties.DynamicDataSourceProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据源工厂
 *
 * @author lizhifu
 * @since 2023/6/12
 */
@Slf4j
public class DynamicDataSourceConfig {
    private final DynamicDataSourceProperties properties;
    public DynamicDataSourceConfig(DynamicDataSourceProperties properties) {
        this.properties = properties;
    }
    private DataSource createDataSource(DynamicDataSourceDetailProperties properties) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(properties.getDriverClassName());
        hikariConfig.setJdbcUrl(properties.getUrl());
        hikariConfig.setUsername(properties.getUsername());
        hikariConfig.setPassword(properties.getPassword());
        // 使用默认数据源的属性配置
        return new HikariDataSource(hikariConfig);
    }
    @Bean
    @ConditionalOnProperty(prefix = DynamicDataSourceProperties.PREFIX, name = "mode", havingValue = ShardingConstants.MASTER_SLAVE)
    public DataSource masterSlave() {
        log.info("读写分离初始化配置的数据源");
        DataSource master = createDataSource(properties.getDatasourceMap().get(ShardingConstants.DEFAULT_MASTER));
        DataSource slave = createDataSource(properties.getDatasourceMap().get(ShardingConstants.DEFAULT_SLAVE));

        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
        // 设置默认数据源
        dynamicRoutingDataSource.setDefaultTargetDataSource(master);

        // 添加数据源
        Map<Object, Object> targetDataSources = new HashMap<>(16);
        targetDataSources.put(ShardingConstants.DEFAULT_SLAVE, slave);
        dynamicRoutingDataSource.setTargetDataSources(targetDataSources);
        return dynamicRoutingDataSource;
    }

    @Bean
    @ConditionalOnProperty(prefix = DynamicDataSourceProperties.PREFIX, name = "mode", havingValue = ShardingConstants.SHARDING)
    public DataSource sharding() {
        log.info("分库分表初始化配置的数据源");
        Map<String, DynamicDataSourceDetailProperties> datasourceMap = properties.getDatasourceMap();
        DataSource master = createDataSource(properties.getDatasourceMap().get(ShardingConstants.DEFAULT_MASTER));

        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
        // 设置默认数据源
        dynamicRoutingDataSource.setDefaultTargetDataSource(master);

        log.info("分库分表初始化配置的数据源,默认数据源key:{}", ShardingConstants.DEFAULT_MASTER);
        DynamicDataSourceContextHolder.setDataSourceMap(ShardingConstants.DEFAULT_MASTER,master);

        Map<Object, Object> targetDataSources = new HashMap<>(16);
        datasourceMap.forEach((key, value) -> {
            log.info("分库分表初始化配置的数据源key:{}:value{}",key,value);
            DataSource target = createDataSource(value);
            DynamicDataSourceContextHolder.setDataSourceMap(key,target);
            targetDataSources.put(key, target);
        });
        // 添加数据源
        dynamicRoutingDataSource.setTargetDataSources(targetDataSources);

        return dynamicRoutingDataSource;
    }
}
