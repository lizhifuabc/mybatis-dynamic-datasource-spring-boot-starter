package com.tomato.mybatis.dynamic.datasource.properties;

import lombok.Data;

/**
 * 动态数据源配置
 *
 * @author lizhifu
 * @since 2023/6/13
 */
@Data
public class DynamicDataSourceDetailProperties {
    /**
     * 数据源驱动
     */
    private String driverClassName;
    /**
     * 数据源url
     */
    private String url;
    /**
     * 数据源用户名
     */
    private String username;
    /**
     * 数据源密码
     */
    private String password;
}
