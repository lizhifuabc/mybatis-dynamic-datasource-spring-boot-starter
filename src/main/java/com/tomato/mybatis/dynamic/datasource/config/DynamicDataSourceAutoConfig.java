package com.tomato.mybatis.dynamic.datasource.config;

import com.tomato.mybatis.dynamic.datasource.constant.ShardingConstants;
import com.tomato.mybatis.dynamic.datasource.plugin.MybatisDynamicDatasourcePlugin;
import com.tomato.mybatis.dynamic.datasource.plugin.MybatisMasterSlavePlugin;
import com.tomato.mybatis.dynamic.datasource.properties.DynamicDataSourceProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;


/**
 * 动态数据源配置
 *
 * @author lizhifu
 * @since 2023/6/13
 */
@AutoConfiguration
@EnableConfigurationProperties(DynamicDataSourceProperties.class)
@Import({DynamicDataSourceConfig.class})
@Slf4j
public class DynamicDataSourceAutoConfig {
    /**
     * 读写分离插件
     * @return 读写分离插件
     */
    @Bean
    @ConditionalOnProperty(prefix = DynamicDataSourceProperties.PREFIX, name = "mode", havingValue = ShardingConstants.MASTER_SLAVE)
    MybatisMasterSlavePlugin mybatisMasterSlavePlugin() {
        log.info("Mybatis 读写分离插件初始化");
        return new MybatisMasterSlavePlugin();
    }
    /**
     * 分库分表插件
     * @return 分库分表插件
     */
    @Bean
    @ConditionalOnProperty(prefix = DynamicDataSourceProperties.PREFIX, name = "mode", havingValue = ShardingConstants.SHARDING)
    MybatisDynamicDatasourcePlugin mybatisDynamicDatasourcePlugin(DynamicDataSourceProperties dynamicDataSourceProperties) {
        log.info("Mybatis 分库分表插件初始化");
        return new MybatisDynamicDatasourcePlugin(dynamicDataSourceProperties);
    }
}
