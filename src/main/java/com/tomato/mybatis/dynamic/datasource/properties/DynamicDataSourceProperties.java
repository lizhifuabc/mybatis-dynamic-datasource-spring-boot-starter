package com.tomato.mybatis.dynamic.datasource.properties;

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
     * 数据源配置
     */
    private Map<String,DynamicDataSourceDetailProperties> datasourceMap;
}
