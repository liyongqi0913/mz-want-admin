package com.mz;

import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "com.mz")
@EnableScheduling
@MapperScan("com.mz.*.*.dao.mapper")
@EnableTransactionManagement
@ServletComponentScan
public class AdminApplication {
    static Logger log = LoggerFactory.getLogger(AdminApplication.class);
    public static void main(String[] args) {

        SpringApplication.run(AdminApplication.class, args);

    }

    /**
     * SQL执行效率插件
     */
    @Bean
    @Profile({"dev"})// 设置 dev 环境开启
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }

}

