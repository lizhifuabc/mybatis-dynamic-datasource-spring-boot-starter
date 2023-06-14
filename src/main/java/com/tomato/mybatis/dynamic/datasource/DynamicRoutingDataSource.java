package com.tomato.mybatis.dynamic.datasource;

import com.tomato.mybatis.dynamic.datasource.context.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源获取
 *
 * @author lizhifu
 * @since 2023/6/13
 */
@Slf4j
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {
    /**
     * 获取数据源
     * @return 数据源
     */
    @Override
    protected Object determineCurrentLookupKey() {
        String datasource = DynamicDataSourceContextHolder.getDatasource();
        log.info("动态数据源切换，当前数据源：{}",datasource);
        return datasource;
    }
}
