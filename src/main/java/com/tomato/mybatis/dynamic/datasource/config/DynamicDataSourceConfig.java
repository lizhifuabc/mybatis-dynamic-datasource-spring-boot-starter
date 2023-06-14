package com.tomato.mybatis.dynamic.datasource.config;

import com.tomato.mybatis.dynamic.datasource.DynamicRoutingDataSource;
import com.tomato.mybatis.dynamic.datasource.constant.ShardingConstants;
import com.tomato.mybatis.dynamic.datasource.context.DynamicDataSourceContextHolder;
import com.tomato.mybatis.dynamic.datasource.properties.DynamicDataSourceDetailProperties;
import com.tomato.mybatis.dynamic.datasource.properties.DynamicDataSourceProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 数据源工厂
 *
 * @author lizhifu
 * @since 2023/6/12
 */
@Slf4j
public class DynamicDataSourceConfig implements InitializingBean {
    private final DataSource dataSource;
    private final DynamicDataSourceProperties properties;
    public DynamicDataSourceConfig(DataSource dataSource, DynamicDataSourceProperties properties) {
        this.dataSource = dataSource;
        this.properties = properties;
    }
    private DataSource createDataSource(DataSource defaultDataSource, DynamicDataSourceDetailProperties properties) {
        HikariDataSource defaultHikariDataSource = (HikariDataSource) defaultDataSource;
        Properties defaultDataSourceProperties = defaultHikariDataSource.getDataSourceProperties();
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(properties.getDriverClassName());
        hikariConfig.setJdbcUrl(properties.getUrl());
        hikariConfig.setUsername(properties.getUsername());
        hikariConfig.setPassword(properties.getPassword());
        // 使用默认数据源的属性配置
        defaultDataSourceProperties.forEach((key, value) -> hikariConfig.addDataSourceProperty(String.valueOf(key), value));
        return new HikariDataSource(hikariConfig);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
        // 设置默认数据源
        dynamicRoutingDataSource.setDefaultTargetDataSource(dataSource);

        log.info("初始化配置的数据源,默认数据源key:{}", ShardingConstants.DEFAULT_MASTER);
        DynamicDataSourceContextHolder.setDataSourceMap(ShardingConstants.DEFAULT_MASTER,dataSource);

        Map<Object, Object> targetDataSources = new HashMap<>(16);
        properties.getDatasourceMap().forEach((key, value) -> {
            log.info("初始化配置的数据源key:{}:value{}",key,value);
            DataSource target = createDataSource(dataSource, value);
            DynamicDataSourceContextHolder.setDataSourceMap(key,target);
            targetDataSources.put(key, target);
        });
        // 添加数据源
        dynamicRoutingDataSource.setTargetDataSources(targetDataSources);
    }
}
