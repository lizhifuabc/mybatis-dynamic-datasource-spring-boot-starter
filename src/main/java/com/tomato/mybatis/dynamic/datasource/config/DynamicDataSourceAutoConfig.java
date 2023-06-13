package com.tomato.mybatis.dynamic.datasource.config;

import com.tomato.mybatis.dynamic.datasource.properties.DynamicDataSourceProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

/**
 * 动态数据源配置
 *
 * @author lizhifu
 * @since 2023/6/13
 */
@AutoConfiguration
@EnableConfigurationProperties(DynamicDataSourceProperties.class)
@Import({DynamicDataSourceConfig.class})
public class DynamicDataSourceAutoConfig {
    
}
