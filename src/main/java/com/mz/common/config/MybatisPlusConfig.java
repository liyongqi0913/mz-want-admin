package com.mz.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.mz.common.config.properties.DruidProperties;
import com.mz.common.config.properties.MutiDataSourceProperties;
import com.mz.common.multiple.DSEnum;
import com.mz.common.multiple.DynamicDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement(order = 2)
@MapperScan(basePackages = {
        "com.mz.modules.*.dao.mapper",
        "com.mz.common.generator.mapper",
        "com.mz.wechat.*.dao.mapper",
        "com.mz.capture.lookdoor.dao.mapper"
})
public class MybatisPlusConfig {

    @Resource
    private DruidProperties druidProperties;


    @Autowired
    MutiDataSourceProperties mutiDataSourceProperties;
    /**
     * 核心数据源
     */
    private DruidDataSource coreDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        return dataSource;
    }

    /**
     * 另一个数据源
     */
    private DruidDataSource bizDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        mutiDataSourceProperties.config(dataSource);
        return dataSource;
    }

    /**
     * 另一个数据源
     */
    private DruidDataSource captureDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        return dataSource;
    }

    /**
     * 单数据源连接池配置
     */
    @Bean
    @ConditionalOnProperty(prefix = "admin", name = "muti-datasource-open", havingValue = "false")
    public DruidDataSource singleDatasource() {
        return coreDataSource();
    }

    /**
     * 多数据源连接池配置
     */
    @Bean
    @ConditionalOnProperty(prefix = "admin", name = "muti-datasource-open", havingValue = "true")
    public DynamicDataSource mutiDataSource() {

        DruidDataSource coreDataSource = coreDataSource();
        DruidDataSource bizDataSource = bizDataSource();
        DruidDataSource captureDataSource = captureDataSource();
        try {
            coreDataSource.init();
            bizDataSource.init();
        } catch (SQLException sql) {
            sql.printStackTrace();
        }

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put(DSEnum.DATA_SOURCE_CORE, coreDataSource);
        hashMap.put(DSEnum.DATA_SOURCE_BIZ, bizDataSource);
        dynamicDataSource.setTargetDataSources(hashMap);
        dynamicDataSource.setDefaultTargetDataSource(coreDataSource);
        return dynamicDataSource;
    }
//
//    /**
//     * 单数据源连接池配置
//     */
//    @Bean
//    public DruidDataSource singleDatasource() {
//        DruidDataSource dataSource = new DruidDataSource();
//        druidProperties.config(dataSource);
//        return dataSource;
//    }

    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
