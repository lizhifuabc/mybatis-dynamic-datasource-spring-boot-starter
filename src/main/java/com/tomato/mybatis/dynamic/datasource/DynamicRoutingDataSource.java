package com.tomato.mybatis.dynamic.datasource;

import com.tomato.mybatis.dynamic.datasource.context.DynamicDataSourceContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源获取
 *
 * @author lizhifu
 * @since 2023/6/13
 */
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {
    /**
     * 获取数据源
     * @return 数据源
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDatasource();
    }
}
