package com.tomato.mybatis.dynamic.datasource.context;

import javax.sql.DataSource;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据源 ThreadLocal 持有者
 *
 * @author lizhifu
 * @since 2023/6/13
 */
public class DynamicDataSourceContextHolder {
    /**
     * 数据源
     */
    private static final ThreadLocal<String> DATASOURCE_HOLDER = new ThreadLocal<>();
    /**
     * 表
     */
    private static final ThreadLocal<String> TABLE_HOLDER = new ThreadLocal<>();
    /**
     * 多数据源合集
     */
    private static final ConcurrentHashMap<String, DataSource> DATASOURCE_MAP = new ConcurrentHashMap<>();
    /**
     * 设置数据源
     *
     * @param datasource 数据源
     */
    public static void setDatasource(String datasource) {
        DATASOURCE_HOLDER.set(datasource);
    }
    /**
     * 获取数据源
     *
     * @return 数据源
     */
    public static String getDatasource() {
        return DATASOURCE_HOLDER.get();
    }
    /**
     * 清除数据源
     */
    public static void clearDatasource() {
        DATASOURCE_HOLDER.remove();
    }
    /**
     * 设置表
     *
     * @param table 表
     */
    public static void setTable(String table) {
        TABLE_HOLDER.set(table);
    }
    /**
     * 获取表
     *
     * @return 表
     */
    public static String getTable() {
        return TABLE_HOLDER.get();
    }
    /**
     * 清除表
     */
    public static void clearTable() {
        TABLE_HOLDER.remove();
    }
    /**
     * 设置多数据源
     * @param name 数据源名称
     * @param dataSource 数据源
     */
    public static void setDataSourceMap(String name, DataSource dataSource) {
        DATASOURCE_MAP.putIfAbsent(name, dataSource);
    }

    /**
     * 获取多数据源
     * @param name 数据源名称
     * @return DataSource 数据源
     */
    public static DataSource getDataSourceMap(String name) {
        return DATASOURCE_MAP.get(name);
    }
}
